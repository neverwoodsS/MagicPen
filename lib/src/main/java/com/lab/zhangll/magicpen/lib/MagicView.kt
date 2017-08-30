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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        shapes.forEach { it.drawOn(canvas) }
    }

    /**
     * 复写触摸事件，通过{@link MagicShape}的containPoint方法
     * 来判断被点击的shapes。
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
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
     * 主要是处理wrap_content的情况
     */
    // TODO: 无法正确的测量圆的宽高，一系列不设置start end的都无法正确测量
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var width = 0
        var height = 0
        // 处理宽度
        if (widthMode == MeasureSpec.EXACTLY) {
            // 指定数值宽度或者match_parent
            width = MeasureSpec.getSize(widthMeasureSpec)
            Log.i("MagicView","width = $width")
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
            width = rightX
        }

        // 处理高度
        if (heightMode == MeasureSpec.EXACTLY) {
            // 指定高度或者match_parent
            height = MeasureSpec.getSize(heightMeasureSpec)
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

            height = bottomY
        }

        Log.i("MagicView", "height = $height width = $width")
        setMeasuredDimension(width, height)
        shapes.forEach {

        }
    }

    fun addShape(shape: MagicShape) {
        shapes.add(shape)
        shape.parent = this
    }

    fun PointF.distanceTo(another: PointF): Float {
        val temp = (x - another.x) * (x - another.x) +
                (y - another.y) * (y - another.y)
        return Math.sqrt(temp.toDouble()).toFloat()
    }
}

fun Float.max(another: Float): Float {
    if (another > this) return another
    return this
}