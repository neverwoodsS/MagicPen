package com.lab.zhangll.magicpen.lib.shapes

/**
 * Created by zhangll on 2017/5/20.
 */
interface MagicLocation {
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