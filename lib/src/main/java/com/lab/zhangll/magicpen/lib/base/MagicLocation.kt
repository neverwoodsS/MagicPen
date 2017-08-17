package com.lab.zhangll.magicpen.lib.base

import android.graphics.PointF

/**
 * Created by zhangll on 2017/5/20.
 * 描述 Shape 位置相关属性
 * start、end 用于描述 Shape 应在位置
 * left、top、right、bottom 用于描述实际绘制位置
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