package com.lab.zhangll.magicpen.lib.base

import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.base.MagicLocation
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/5/22.
 * Shape 之间相对位置关系的运算及状态保留（即刷新后不会失效）
 */
interface MagicRelationship : com.lab.zhangll.magicpen.lib.base.MagicLocation {

    var leftMargin: Float
    var rightMargin: Float
    var topMargin: Float
    var bottomMargin: Float

    val relations: MutableList<() -> Unit>

    private fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> guardParameters(another: T) {
        if (another.start == null || another.end == null) {
            throw Exception("不允许参照对象为空")
        }

        if (width == 0f || height == 0f) {
            throw Exception("宽高不能为空")
        }
    }

    fun reEnd() = android.graphics.PointF(start!!.x + width, start!!.y + height)

    fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> belowOf(another: T) {
        guardParameters(another)

        val func = {
            start = android.graphics.PointF(start?.x ?: another.start!!.x,
                    if (another.end!!.y > another.start!!.y) another.end!!.y else another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> aboveOf(another: T) {
        guardParameters(another)

        val func = {
            start = android.graphics.PointF(start?.x ?: another.start!!.x,
                    (if (another.end!!.y > another.start!!.y) another.start!!.y else another.end!!.y) - height)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> rightOf(another: T) {
        guardParameters(another)

        val func = {
            start = android.graphics.PointF(if (another.end!!.x > another.start!!.x) another.end!!.x else another.start!!.x,
                    start?.y ?: another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> leftOf(another: T) {
        guardParameters(another)

        val func = {
            start = android.graphics.PointF((if (another.end!!.x > another.start!!.x) another.start!!.x else another.end!!.x) - width,
                    start?.y ?: another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> centerIn(another: T) {
        guardParameters(another)

        val func = {
            start = android.graphics.PointF(another.start!!.x / 2 + another.end!!.x / 2 - width / 2,
                    another.start!!.y / 2 + another.end!!.y / 2 - height / 2)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> alignTop(another: T) {
        guardParameters(another)

        val func = {
            start = android.graphics.PointF(start?.x ?: another.start!!.x,
                    another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> alignBottom(another: T) {
        guardParameters(another)

        val func = {
            start = android.graphics.PointF(start?.x ?: another.start!!.x,
                    another.end!!.y - height)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> alignLeft(another: T) {
        guardParameters(another)

        val func = {
            start = android.graphics.PointF(another.start!!.x,
                    start?.y ?: another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }

    fun <T : com.lab.zhangll.magicpen.lib.shapes.MagicShape> alignRight(another: T) {
        guardParameters(another)

        val func = {
            start = android.graphics.PointF(another.end!!.x - width,
                    start?.y ?: another.start!!.y)
            end = reEnd()
        }

        func.invoke()
        relations.add(func)
    }
}