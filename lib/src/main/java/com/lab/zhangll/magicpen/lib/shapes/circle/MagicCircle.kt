package com.lab.zhangll.magicpen.lib.shapes.circle

import android.graphics.Canvas
import android.graphics.Paint
import com.lab.zhangll.magicpen.lib.shapes.*

/**
 * Created by zhangll on 2017/5/20.
 */
class MagicCircle : MagicShape() {
    override var left: Float = 0f
    override var top: Float = 0f
    override var right: Float = 0f
    override var bottom: Float = 0f
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
}