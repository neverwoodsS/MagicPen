package com.lab.zhangll.magicpen.lib.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log

/**
 * Created by zhangll on 2017/5/22.
 */
class MagicLine : MagicShape() {

    override var paint: Paint = Paint()

    val k by lazy { (bottom - top) / (right - left) }
    val b by lazy { top - k * left }

    override fun containPoint(x: Float, y: Float): Boolean {
        // 点到直线距离公式
        val distance = Math.abs(k * x - y + b) / Math.sqrt((k * k + 1).toDouble())
        return distance <= 10
    }

    override fun drawOn(canvas: Canvas?) {
        canvas?.drawLine(left, top, right, bottom, paint)
        if (canvas == null) {
            Log.i("MagicLine", "fuck")
        }
        Log.i("MagicLine", "left=$left,top=$top,right=$right,bottom=$bottom")
    }
}