package com.lab.zhangll.magicpen.lib.shapes.line

import android.graphics.Canvas
import android.graphics.Paint
import com.lab.zhangll.magicpen.lib.setting.MagicGesture
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/5/22.
 */
class MagicLine : MagicShape() {

    override var left: Float = 0f
    override var top: Float = 0f
    override var right: Float = 0f
    override var bottom: Float = 0f
    override var paint: Paint = Paint()

    val k by lazy { (bottom - top) / (right - left) }
    val b by lazy { top - k * left }

    override fun containPoint(x: Float, y: Float): Boolean {
        // 点到直线距离公式
        val distance = Math.abs(k * x - y + b) / Math.sqrt((k * k + 1).toDouble())
        return distance <= 10
    }

    override fun drawOn(canvas: Canvas?) = canvas?.drawLine(left, top, right, bottom, paint)
}