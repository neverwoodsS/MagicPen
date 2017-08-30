package com.lab.zhangll.magicpen.lib.shapes

import android.view.View
import com.lab.zhangll.magicpen.lib.base.*

/**
 * Created by zhangll on 2017/5/20.
 * Shape 基类，用于整合多个协议的实现
 */
abstract class MagicShape : MagicLocation(), MagicDraw, MagicMotion {
    lateinit var parent: View
    open var gesture: MagicGesture? = null

    open fun reBounds() {
        left = start?.x ?: 0f
        top = start?.y ?: 0f
        right = end?.x ?: 0f
        bottom = end?.y ?: 0f
    }

    override fun invalidate() {
        relations.forEach { it.invoke() }
        parent.invalidate()
    }

    override fun invalidateDirectly() {
        parent.invalidate()
    }
}