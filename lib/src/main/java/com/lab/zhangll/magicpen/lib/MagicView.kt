package com.lab.zhangll.magicpen.lib

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
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
        // let shape draw itself
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

    fun addShape(shape: MagicShape) {
        shapes.add(shape)
        shape.parent = this
    }
}

fun PointF.distanceTo(another: PointF): Float {
    val temp = (x - another.x) * (x - another.x) +
            (y - another.y) * (y - another.y)
    return Math.sqrt(temp.toDouble()).toFloat()
}