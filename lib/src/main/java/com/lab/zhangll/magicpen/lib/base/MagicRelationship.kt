package com.lab.zhangll.magicpen.lib.base

import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/5/22.
 * Shape 之间相对位置关系的运算及状态保留（即刷新后不会失效）
 */
abstract class MagicRelationship : IMagicLocation {
    open var centerInParent = false
    open var centerHorizontal = false
    open var centerVertical = false

    // 这里存疑，可以考虑用十六进制的flag来做
    open var alignParentStart = false
    open var alignParentEnd = false
    open var alignParentTop = false
    open var alignParentBottom = false

    var leftMargin = 0f
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

    var rightMargin = 0f
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

    var topMargin = 0f
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

    var bottomMargin = 0f
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

    val relations: MutableList<() -> Unit> = mutableListOf()

    private fun <T : MagicShape> guardParameters(another: T) {
        if (another.start == null || another.end == null) {
            throw Exception("不允许参照对象为空")
        }

        if (width == 0f || height == 0f) {
            throw Exception("宽高不能为空")
        }
    }

    fun reEnd() = PointF(start!!.x + width, start!!.y + height)

    fun <T : MagicShape> belowOf(another: T) {
        guardParameters(another)

        val func = {
            start = PointF(start?.x ?: another.start!!.x,
                    if (another.end!!.y > another.start!!.y) another.end!!.y else another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : MagicShape> aboveOf(another: T) {
        guardParameters(another)

        val func = {
            start = PointF(start?.x ?: another.start!!.x,
                    (if (another.end!!.y > another.start!!.y) another.start!!.y else another.end!!.y) - height)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : MagicShape> rightOf(another: T) {
        guardParameters(another)

        val func = {
            start = PointF(if (another.end!!.x > another.start!!.x) another.end!!.x else another.start!!.x,
                    start?.y ?: another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : MagicShape> leftOf(another: T) {
        guardParameters(another)

        val func = {
            start = PointF((if (another.end!!.x > another.start!!.x) another.start!!.x else another.end!!.x) - width,
                    start?.y ?: another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : MagicShape> centerIn(another: T) {
        guardParameters(another)

        val func = {
            start = PointF(another.start!!.x / 2 + another.end!!.x / 2 - width / 2,
                    another.start!!.y / 2 + another.end!!.y / 2 - height / 2)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : MagicShape> alignTop(another: T) {
        guardParameters(another)

        val func = {
            start = PointF(start?.x ?: another.start!!.x,
                    another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : MagicShape> alignBottom(another: T) {
        guardParameters(another)

        val func = {
            start = PointF(start?.x ?: another.start!!.x,
                    another.end!!.y - height)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : MagicShape> alignLeft(another: T) {
        guardParameters(another)

        val func = {
            start = PointF(another.start!!.x,
                    start?.y ?: another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : MagicShape> alignRight(another: T) {
        guardParameters(another)

        val func = {
            start = PointF(another.end!!.x - width,
                    start?.y ?: another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }
}