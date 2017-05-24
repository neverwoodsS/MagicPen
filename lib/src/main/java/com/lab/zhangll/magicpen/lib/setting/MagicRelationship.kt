package com.lab.zhangll.magicpen.lib.setting

import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/5/22.
 */
interface MagicRelationship {
    var start: PointF?
    var end: PointF?

    var width: Float?
    var height: Float?

    var leftMargin: Float
    var rightMargin: Float
    var topMargin: Float
    var bottomMargin: Float

    private fun <T : MagicShape> guardParameters(another: MagicSetting<T>) {
        if (another.start == null || another.end == null) {
            throw Exception("不允许参照对象为空")
        }

        if (width == null || height == null) {
            throw Exception("宽高不能为空")
        }
    }

    fun reEnd() = PointF(start!!.x + width!!, start!!.y + height!!)

    fun <T : MagicShape> belowOf(another: MagicSetting<T>) {
        guardParameters(another)

        start = PointF(start?.x ?: another.start!!.x,
                another.end!!.y)
        end = reEnd()
    }

    fun <T : MagicShape> aboveOf(another: MagicSetting<T>) {
        guardParameters(another)

        start = PointF(start?.x ?: another.start!!.x,
                another.start!!.y - height!!)
        end = reEnd()
    }

    fun <T : MagicShape> rightOf(another: MagicSetting<T>) {
        guardParameters(another)

        start = PointF(another.end!!.x,
                start?.y ?: another.start!!.y)
        end = reEnd()
    }

    fun <T : MagicShape> leftOf(another: MagicSetting<T>) {
        guardParameters(another)

        start = PointF(another.start!!.x - width!!,
                start?.y ?: another.start!!.y)
        end = reEnd()
    }

    fun <T : MagicShape> centerIn(another: MagicSetting<T>) {
        guardParameters(another)

        start = PointF(another.start!!.x / 2 + another.end!!.x / 2 - width!! / 2,
                another.start!!.y / 2 + another.end!!.y / 2 - height!! / 2)
        end = reEnd()
    }

    fun <T : MagicShape> alignTop(another: MagicSetting<T>) {
        guardParameters(another)

        start = PointF(start?.x ?: another.start!!.x,
                another.start!!.y)
        end = reEnd()
    }

    fun <T : MagicShape> alignBottom(another: MagicSetting<T>) {
        guardParameters(another)

        start = PointF(start?.x ?: another.start!!.x,
                another.end!!.y - height!!)
        end = reEnd()
    }

    fun <T : MagicShape> alignLeft(another: MagicSetting<T>) {
        guardParameters(another)

        start = PointF(another.start!!.x,
                start?.y ?: another.start!!.y)
        end = reEnd()
    }

    fun <T : MagicShape> alignRight(another: MagicSetting<T>) {
        guardParameters(another)

        start = PointF(another.end!!.x - width!!,
                start?.y ?: another.start!!.y)
        end = reEnd()
    }
}