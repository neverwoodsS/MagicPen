package com.lab.zhangll.magicpen.lib.setting

/**
 * Created by zhangll on 2017/5/23.
 */
interface MagicMotion {
    fun invalidate()

    fun moveBy(x: Float, y: Float)
    fun moveToOrigin()

    fun smoothMoveTo(targetX: Float, targetY: Float)
    fun smoothMoveCenterTo(targetX: Float, targetY: Float)
    fun smoothMoveToOrigin()
}