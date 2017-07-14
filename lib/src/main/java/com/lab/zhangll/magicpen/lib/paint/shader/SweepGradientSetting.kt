package com.lab.zhangll.magicpen.lib.paint.shader

import android.graphics.Matrix

/**
 * Created by zhangll on 2017/7/14.
 */
class SweepGradientSetting {
    var centerX: Float = 0f
    var centerY: Float = 0f
    lateinit var color: Array<Int>
    lateinit var percent: Array<Float>
    var matrix: Matrix? = null
}