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

    override fun drawOn(canvas: Canvas?) = canvas?.drawText(text, left, top, paint)

    override fun containPoint(x: Float, y: Float) = containInRect(x, y)
}