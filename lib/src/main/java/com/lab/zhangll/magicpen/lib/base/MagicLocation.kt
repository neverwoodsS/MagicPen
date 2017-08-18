package com.lab.zhangll.magicpen.lib.base

import android.graphics.PointF

/**
 * Created by zhangll on 2017/8/18.
 */
interface MagicLocation {
    var start: PointF?
    var end: PointF?

    var width: Float
    var height: Float

    var left: Float
    var top: Float
    var right: Float
    var bottom: Float
}

var MagicLocation.centerX
    get() = (left + right) / 2
    private set(value) {}

var MagicLocation.centerY
    get() = (top + bottom) / 2
    private set(value) {}