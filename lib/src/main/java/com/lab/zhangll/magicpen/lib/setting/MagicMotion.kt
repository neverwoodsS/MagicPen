package com.lab.zhangll.magicpen.lib.setting

/**
 * Created by zhangll on 2017/5/23.
 */
interface MagicMotion {
    fun moveBy(x: Float, y: Float)
    fun moveToOrigin()

    fun smoothMoveTo(targetX: Float, targetY: Float)
    fun smoothMoveToOrigin()
}