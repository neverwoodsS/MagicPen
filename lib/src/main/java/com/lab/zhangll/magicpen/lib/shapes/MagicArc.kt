package com.lab.zhangll.magicpen.lib.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/7/12.
 */
class MagicArc : MagicShape() {
    override var paint: Paint = Paint()

    var center = PointF(0f, 0f)
        set(value) {
            field = value
            reLocate()
        }
    var radius: Float = 0f
        set(value) {
            width = value * 2
            height = value * 2
            field = value
            reLocate()
        }

    var startAngle = 0f
    var sweepAngle = 360f

    var useCenter = false

    override fun drawOn(canvas: Canvas?) = canvas?.drawArc(RectF(left, top, right, bottom), startAngle, sweepAngle, useCenter, paint)

    override fun containPoint(x: Float, y: Float) = false

    fun reLocate() {
        start = PointF(center.x - radius, center.y - radius)
        end = PointF(center.x + radius, center.y + radius)
    }

    override fun reBounds() {
        reLocate()
        super.reBounds()
    }
}