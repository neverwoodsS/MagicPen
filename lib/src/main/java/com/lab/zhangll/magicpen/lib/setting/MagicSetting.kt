package com.lab.zhangll.magicpen.lib.setting

import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/5/20.
 */
abstract class MagicSetting<T : MagicShape>(open val shape: T) : MagicRelationship, MagicMotion by shape {

    /** 用于生成对应图形类的方法，由具体的子类实现 */
    abstract fun product(shape: T): T

    override var start: PointF? = null
    override var end: PointF? = null

    override var width: Float? = null
    override var height: Float? = null

    override val relations = mutableListOf<() -> Unit>()

    var left = 0f
        set(value) {
            field = value
            start = PointF(value, top)
            reCount()
        }

        get() = start?.x ?: 0f

    var top = 0f
        set(value) {
            field = value
            start = PointF(left, value)
            reCount()
        }

        get() = start?.y ?: 0f

    var right = 0f
        set(value) {
            field = value
            end = PointF(value, bottom)
            reCount()
        }

        get() = end?.x ?: 0f

    var bottom = 0f
        set(value) {
            field = value
            end = PointF(right, value)
            reCount()
        }

        get() = end?.y ?: 0f

    // 手势处理
    var gesture: MagicGesture? = null
    var gestureSet: (MagicGesture.() -> Unit)? = null

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

    fun reCount() {
        width = right - left
        height = bottom - top
    }

    override fun invalidate() {
        relations.forEach { it.invoke() }
        product(shape)
        shape.invalidate()
    }
}