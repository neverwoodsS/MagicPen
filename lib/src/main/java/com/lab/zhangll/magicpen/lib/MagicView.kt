package com.lab.zhangll.magicpen.lib

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/5/20.
 */
class MagicView(context: Context) : View(context) {
    // shape集合，让每一个shape自身调用onDraw方法去绘制自己
    private val shapes: MutableList<MagicShape> = mutableListOf()
    // 手指按下触摸到的Shape
    var touchDownShapes: List<MagicShape> = mutableListOf()

    var downPoint = PointF(0f, 0f)

    // if measureX or Y correctly this flag will be true
    var measureX = false
    var measureY = false

    var mWidth = 0
    var mHeight = 0

    var shapeGravity = ShapeGravity.NULL

    /**
     * let shape draw itself
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        shapes.forEach { it.drawOn(canvas) }
    }

    fun addShape(shape: MagicShape) {
        shapes.add(shape)
        shape.parent = this
    }

    /**
     * 复写触摸事件，通过{@link MagicShape}的containPoint方法
     * 来判断被点击的shapes。
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                downPoint = PointF(event.x, event.y)
                touchDownShapes = shapes.filter { it.containPoint(event.x, event.y) }
            }

            MotionEvent.ACTION_MOVE -> {
                val nowPoint = PointF(event.x, event.y)
                if (nowPoint.distanceTo(downPoint) > 10) {
                    touchDownShapes.forEach {
                        it.gesture?.onDragBy?.invoke(nowPoint.x - downPoint.x, nowPoint.y - downPoint.y)
                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                val nowPoint = PointF(event.x, event.y)
                if (nowPoint.distanceTo(downPoint) < 10) {
                    touchDownShapes.forEach { it.gesture?.onClick?.invoke() }
                } else {
                    touchDownShapes.forEach { it.gesture?.onRelease?.invoke(nowPoint.x, nowPoint.y) }
                }
            }
        }

        return true
    }

    /**
     * deal with the wrap_content situation
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // step 1:measure width and height correctly,onMeasure method
        // maybe invoke many times by system,once we get correct width
        // and height,stop measure.
        measureWidthAndHeight(widthMeasureSpec, heightMeasureSpec)

        Log.i("MagicView", "height = $mHeight width = $mWidth")

        // step 2:use parameter set by user to change the start and
        // the end point's x or y
        val centerX = mWidth / 2.0
        val centerY = mHeight / 2.0
        shapes.forEach {
            if (it.centerInParent) {
                it.centerHorizontal(centerX)
                it.centerVertical(centerY)
            } else if (it.centerHorizontal) {
                it.centerHorizontal(centerX)
            } else if (it.centerVertical) {
                it.centerVertical(centerY)
            }
        }
        // TODO alignParent wait to be finish
        setMeasuredDimension(mWidth, mHeight)
    }

    private fun measureWidthAndHeight(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        // first measure width
        if (!measureX) {
            // 处理宽度
            if (widthMode == MeasureSpec.EXACTLY) {
                // 指定数值宽度或者match_parent
                mWidth = MeasureSpec.getSize(widthMeasureSpec)
                Log.i("MagicView", "width = $mWidth")
                measureX = true
            } else if (widthMode == MeasureSpec.AT_MOST) {
                // 一般为wrap_content
                var rightX = -1
                shapes.forEach {
                    if (rightX < it.start!!.y.max(it.end!!.y)) {
                        rightX = it.start!!.y.max(it.end!!.y).toInt()
                    }
                    Log.i("MagicView", "width start: " + it.start.toString() +
                            " end: " + it.end.toString())
                }
                mWidth = rightX
                measureX = true
            }
        }

        // second measure height
        if (!measureY) {
            // 处理高度
            if (heightMode == MeasureSpec.EXACTLY) {
                // 指定高度或者match_parent
                mHeight = MeasureSpec.getSize(heightMeasureSpec)
                measureY = true
            } else if (heightMode == MeasureSpec.AT_MOST) {
                // 一般为wrap_content
                var bottomY = -1
                shapes.forEach {
                    if (bottomY < it.start!!.y.max(it.end!!.y)) {
                        bottomY = it.start!!.y.max(it.end!!.y).toInt()
                    }

                    Log.i("MagicView", "height start: " + it.start.toString() +
                            " end: " + it.end.toString())
                }

                mHeight = bottomY
                measureY = true
            }
        }
    }

    private fun layoutShape(centerX: Int, centerY: Int) {
        // step 1:use shapeGravity flag to layout all shapes
        if (shapeGravity.xor(0xffff0000) == 0L) {
            // START | TOP
        } else if (shapeGravity.xor(0xff00ff00) == 0L) {
            // START | BOTTOM
        } else if (shapeGravity.xor(0x00ff00ff) == 0L) {
            // END | TOP
        } else if (shapeGravity.xor(0x0000ffff) == 0L) {
            // END | BOTTOM
        } else if (shapeGravity.xor(ShapeGravity.START) == 0L) {
            // START
        } else if (shapeGravity.xor(ShapeGravity.END) == 0L) {
            // END
        } else if (shapeGravity.xor(ShapeGravity.TOP) == 0L) {
            // TOP
        } else if (shapeGravity.xor(ShapeGravity.BOTTOM) == 0L) {
            // BOTTOM
        }

        // step 2:if shape itself has a layout flag such as
        // centerInParent,relayout the shape,this means shapeGravity
        // will lose efficacy
    }

    fun PointF.distanceTo(another: PointF): Float {
        val temp = (x - another.x) * (x - another.x) +
                (y - another.y) * (y - another.y)
        return Math.sqrt(temp.toDouble()).toFloat()
    }

    fun Float.max(another: Float): Float {
        if (another > this) return another
        return this
    }

}
