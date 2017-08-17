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
abstract class MagicShape : MagicDraw, MagicMotion, MagicRelationship {
    lateinit var parent: View
    override val relations: MutableList<() -> Unit> = mutableListOf()
    open var gesture: MagicGesture? = null

    override var left: Float = 0f
    override var top: Float = 0f
    override var right: Float = 0f
    override var bottom: Float = 0f

    override var start: PointF? = null
        set(value) {
            field = value
            left = value?.x ?: 0f
            top = value?.y ?: 0f
        }

    override var end: PointF? = null
        set(value) {
            field = value
            right = value?.x ?: 0f
            bottom = value?.y ?: 0f
        }

    override var width: Float = 0f
    override var height: Float = 0f

    override var leftMargin = 0f
        set(value) {
            field = value
            val func = {
                start = PointF((start?.x ?: 0f) + value,
                        start?.y ?: 0f)
                end = reEnd()
            }
            func.invoke()
            relations.add(func)
        }

    override var rightMargin = 0f
        set(value) {
            field = value
            val func = {
                start = PointF((start?.x ?: 0f) - value,
                        start?.y ?: 0f)
                end = reEnd()
            }
            func.invoke()
            relations.add(func)
        }

    override var topMargin = 0f
        set(value) {
            field = value
            val func = {
                start = PointF(start?.x ?: 0f,
                        (start?.y ?: 0f) + value)
                end = reEnd()
            }
            func.invoke()
            relations.add(func)
        }

    override var bottomMargin = 0f
        set(value) {
            field = value
            val func = {
                start = PointF(start?.x ?: 0f,
                        (start?.y ?: 0f) - value)
                end = reEnd()
            }
            func.invoke()
            relations.add(func)
        }

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