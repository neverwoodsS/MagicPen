package com.lab.zhangll.magicpen.lib.shapes.rect

import android.graphics.Canvas
import android.graphics.Paint
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/6/5.
 */
class MagicRect : MagicShape() {
    override var left: Float = 0f
    override var top: Float = 0f
    override var right: Float = 0f
    override var bottom: Float = 0f
    override var paint: Paint = Paint()

    override fun containPoint(x: Float, y: Float) = containInRect(x, y)

    override fun drawOn(canvas: Canvas?) = canvas?.drawRect(left, top, right, bottom, paint)
}