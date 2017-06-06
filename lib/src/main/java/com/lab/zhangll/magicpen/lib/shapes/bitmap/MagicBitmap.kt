package com.lab.zhangll.magicpen.lib.shapes.bitmap

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/6/6.
 */
class MagicBitmap : MagicShape() {
    var bitmap: Bitmap? = null
    override var paint: Paint = Paint()

    override fun drawOn(canvas: Canvas?) = canvas?.drawBitmap(bitmap, left, top, paint)
    override fun containPoint(x: Float, y: Float) = containInRect(x, y)
}