package com.lab.zhangll.magicpen.lib.shapes.text

import android.graphics.Canvas
import android.graphics.Paint
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/5/26.
 */
class MagicText : MagicShape() {
    override var paint: Paint = Paint()

    lateinit var text: String

    // 因为 drawText 提供的 y 值实际上是 baseline 的 y 值，而不是起止点 y 值，所以需经过换算
    override fun drawOn(canvas: Canvas?) = canvas?.drawText(text, left, bottom - paint.fontMetrics.bottom, paint)

    override fun containPoint(x: Float, y: Float) = containInRect(x, y)
}