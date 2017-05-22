package com.lab.zhangll.magicpen.lib

import android.graphics.Canvas
import android.graphics.Paint

/**
 * Created by zhangll on 2017/5/20.
 */
interface MagicDraw {
    var paint: Paint
    fun drawOn(canvas: Canvas?)
}