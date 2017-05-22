package com.lab.zhangll.magicpen.lib.shapes

import android.graphics.Canvas
import android.graphics.Paint

/**
 * Created by zhangll on 2017/5/20.
 */
interface MagicDraw {
    var paint: Paint
    fun drawOn(canvas: Canvas?): Unit?
}