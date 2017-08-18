package com.lab.zhangll.magicpen.lib.shapes

import android.graphics.PointF
import android.view.View
import com.lab.zhangll.magicpen.lib.base.MagicDraw
import com.lab.zhangll.magicpen.lib.base.MagicMotion
import com.lab.zhangll.magicpen.lib.base.MagicGesture
import com.lab.zhangll.magicpen.lib.base.MagicRelationship

/**
 * Created by zhangll on 2017/5/20.
 * Shape 基类，用于整合多个协议的实现
 */
abstract class MagicShape : MagicRelationship(), MagicDraw, MagicMotion {
    lateinit var parent: View
    open var gesture: MagicGesture? = null

    abstract fun containPoint(x: Float, y: Float): Boolean

    fun containInRect(x: Float, y: Float): Boolean {
        return x in left..right
                && y in top..bottom
    }

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