package com.lab.zhangll.magicpen.lib.setting

/**
 * Created by zhangll on 2017/5/22.
 */
class MagicGesture {
    var onClick: (() -> Unit)? = null
    var onDragBy: ((x: Float, y: Float) -> Unit)? = null
    var onRelease: (() -> Unit)? = null
}