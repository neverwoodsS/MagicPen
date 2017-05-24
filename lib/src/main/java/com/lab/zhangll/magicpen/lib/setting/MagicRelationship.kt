package com.lab.zhangll.magicpen.lib.setting

import android.graphics.PointF

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

    private fun guardParameters(another: MagicSetting) {
        if (another.start == null || another.end == null) {
            throw Exception("不允许参照对象为空")
        }

        if (width == null || height == null) {
            throw Exception("宽高不能为空")
        }
    }

    fun reEnd() = PointF(start!!.x + width!!, start!!.y + height!!)

    fun belowOf(another: MagicSetting) {
        guardParameters(another)

        start = PointF(start?.x ?: another.start!!.x,
                another.end!!.y)
        end = reEnd()
    }

    fun aboveOf(another: MagicSetting) {
        guardParameters(another)

        start = PointF(start?.x ?: another.start!!.x,
                another.start!!.y - height!!)
        end = reEnd()
    }

    fun rightOf(another: MagicSetting) {
        guardParameters(another)

        start = PointF(another.end!!.x,
                start?.y ?: another.start!!.y)
        end = reEnd()
    }

    fun leftOf(another: MagicSetting) {
        guardParameters(another)

        start = PointF(another.start!!.x - width!!,
                start?.y ?: another.start!!.y)
        end = reEnd()
    }

    fun centerIn(another: MagicSetting) {
        guardParameters(another)

        start = PointF(another.start!!.x / 2 + another.end!!.x / 2 - width!! / 2,
                another.start!!.y / 2 + another.end!!.y / 2 - height!! / 2)
        end = reEnd()
    }

    fun alignTop(another: MagicSetting) {
        guardParameters(another)

        start = PointF(start?.x ?: another.start!!.x,
                another.start!!.y)
        end = reEnd()
    }

    fun alignBottom(another: MagicSetting) {
        guardParameters(another)

        start = PointF(start?.x ?: another.start!!.x,
                another.end!!.y - height!!)
        end = reEnd()
    }

    fun alignLeft(another: MagicSetting) {
        guardParameters(another)

        start = PointF(another.start!!.x,
                start?.y ?: another.start!!.y)
        end = reEnd()
    }

    fun alignRight(another: MagicSetting) {
        guardParameters(another)

        start = PointF(another.end!!.x - width!!,
                start?.y ?: another.start!!.y)
        end = reEnd()
    }
}