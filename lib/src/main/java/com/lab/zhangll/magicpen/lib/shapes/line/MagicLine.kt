package com.lab.zhangll.magicpen.lib.shapes.line

import android.graphics.Canvas
import android.graphics.Paint
import com.lab.zhangll.magicpen.lib.MagicShape

/**
 * Created by zhangll on 2017/5/22.
 */
class MagicLine(
        override var left: Float,
        override var top: Float,
        override var right: Float,
        override var bottom: Float,
        override var paint: Paint
) : MagicShape {
    override fun drawOn(canvas: Canvas?) = canvas?.drawLine(left, top, right, bottom, paint)
}