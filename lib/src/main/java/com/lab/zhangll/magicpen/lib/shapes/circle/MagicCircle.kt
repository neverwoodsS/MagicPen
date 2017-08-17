package com.lab.zhangll.magicpen.lib.shapes.circle

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.shapes.*

/**
 * Created by zhangll on 2017/5/20.
 */
class MagicCircle : MagicShape() {
    var center: PointF? = PointF(0f, 0f)
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

    override var paint: Paint = Paint()

    override fun containPoint(x: Float, y: Float): Boolean {
        if (containInRect(x, y)) {
            val dx = x - centerX
            val dy = y - centerY
            return dx * dx + dy * dy <= width * width / 4
        }
        return false
    }

    override fun drawOn(canvas: Canvas?) = canvas?.drawCircle(centerX, centerY, width / 2, paint)

    fun reLocate() {
        if (center != null) {
            start = PointF(center!!.x - radius, center!!.y - radius)
            end = PointF(center!!.x + radius, center!!.y + radius)
        }
    }

    override fun reBounds() {
        reLocate()
        super.reBounds()
    }
}