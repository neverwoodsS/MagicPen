package com.lab.zhangll.magicpen.lib

import android.content.Context
import android.graphics.Canvas
import android.view.View

/**
 * Created by zhangll on 2017/5/20.
 */
class MagicView(context: Context) : View(context) {
    val shapes: MutableList<MagicShape> = mutableListOf()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        shapes.forEach { it.drawOn(canvas) }
    }
}