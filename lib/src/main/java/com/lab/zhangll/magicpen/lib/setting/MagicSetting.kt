package com.lab.zhangll.magicpen.lib.setting

import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/5/20.
 */
abstract class MagicSetting<T : MagicShape>(shape: T) : MagicRelationship, MagicMotion by shape {

    /** 用于生成对应图形类的方法，由具体的子类实现 */
    abstract fun product(shape: T): T

    override var start: PointF? = null
    override var end: PointF? = null

    override var width: Float? = null
    override var height: Float? = null

    var left = 0f
        set(value) {
            field = value
            start = PointF(left, top)
            reCount()
        }

    var top = 0f
        set(value) {
            field = value
            start = PointF(left, top)
            reCount()
        }

    var right = 0f
        set(value) {
            field = value
            end = PointF(right, bottom)
            reCount()
        }

    var bottom = 0f
        set(value) {
            field = value
            end = PointF(right, bottom)
            reCount()
        }

    // 手势处理
    var gesture: MagicGesture? = null
    var gestureSet: (MagicGesture.() -> Unit)? = null

    override var leftMargin = 0f
        set(value) {
            field = value
            start = PointF((start?.x ?: 0f) + value,
                    start?.y ?: 0f)
            end = reEnd()
        }

    override var rightMargin = 0f
        set(value) {
            field = value
            start = PointF((start?.x ?: 0f) - value,
                    start?.y ?: 0f)
            end = reEnd()
        }

    override var topMargin = 0f
        set(value) {
            field = value
            start = PointF(start?.x ?: 0f,
                    (start?.y ?: 0f) + value)
            end = reEnd()
        }

    override var bottomMargin = 0f
        set(value) {
            field = value
            start = PointF(start?.x ?: 0f,
                    (start?.y ?: 0f) - value)
            end = reEnd()
        }

    fun reCount() {
        width = right - left
        height = bottom - top
    }
}