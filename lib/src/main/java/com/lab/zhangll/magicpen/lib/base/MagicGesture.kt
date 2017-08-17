package com.lab.zhangll.magicpen.lib.base

/**
 * Created by zhangll on 2017/5/22.
 */
class MagicGesture {
    var onClick = { Unit }
    var onDragBy = { x: Float, y: Float -> Unit }
    var onRelease = { x: Float, y: Float -> Unit }
}