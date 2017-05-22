package com.lab.zhangll.magicpen.lib.shapes

import com.lab.zhangll.magicpen.lib.setting.MagicGesture

/**
 * Created by zhangll on 2017/5/20.
 */
interface MagicShape : MagicLocation, MagicDraw {
    var gesture: MagicGesture?
    fun containPoint(x: Float, y: Float): Boolean
}

fun MagicShape.containInRect(x: Float, y: Float): Boolean {
    return x in left..right
            && y in top..bottom
}