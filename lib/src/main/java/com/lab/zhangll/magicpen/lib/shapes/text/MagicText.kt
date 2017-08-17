package com.lab.zhangll.magicpen.lib.shapes.text

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/5/26.
 */
class MagicText : MagicShape() {
    var center = PointF(0f, 0f)
        set(value) {
            field = value
            recountWidthAndHeight()
        }

    var content: String? = null
        set(value) {
            field = value
            recountWidthAndHeight()
        }

    override var paint: Paint = Paint()
        set(value) {
            field = value
            recountWidthAndHeight()
        }

    // 因为 drawText 提供的 y 值实际上是 baseline 的 y 值，而不是起止点 y 值，所以需经过换算
    override fun drawOn(canvas: Canvas?) = canvas?.drawText(content, left, bottom - paint.fontMetrics.bottom, paint)

    override fun containPoint(x: Float, y: Float) = containInRect(x, y)

    private fun recountWidthAndHeight() {
        width = paint.measureText(content)

        val fontMetrics = paint.fontMetrics
        height = fontMetrics.descent - fontMetrics.ascent

        start = PointF(center.x - width / 2, center.y - height / 2)
        end = PointF(center.x + width / 2, center.y + height / 2)
    }
}