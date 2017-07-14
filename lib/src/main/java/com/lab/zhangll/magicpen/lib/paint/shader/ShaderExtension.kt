package com.lab.zhangll.magicpen.lib.paint.shader

import android.content.Context
import android.graphics.SweepGradient

/**
 * Created by zhangll on 2017/7/14.
 */
fun Context.sweepGradient(setting: SweepGradientSetting.() -> Unit): SweepGradient {
    val sweepSetting = SweepGradientSetting().apply { setting() }
    return with(sweepSetting) {
        SweepGradient(centerX, centerY, color.toIntArray(), percent.toFloatArray()).apply {
            setLocalMatrix(matrix)
        }
    }
}