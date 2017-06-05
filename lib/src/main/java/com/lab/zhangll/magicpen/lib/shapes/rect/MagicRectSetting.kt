package com.lab.zhangll.magicpen.lib.shapes.rect

import android.graphics.Paint
import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.setting.MagicSetting

/**
 * Created by zhangll on 2017/6/5.
 */
class MagicRectSetting(shape: MagicRect) : MagicSetting<MagicRect>(shape) {
    var center: PointF? = null

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

    var paint: Paint? = null

    override fun product(shape: MagicRect): MagicRect {
        if (center != null && width != null && height != null) {
            start = PointF(center!!.x - width!! / 2, center!!.y - height!! / 2)
            end = PointF(center!!.x + width!! / 2, center!!.y + height!! / 2)
        }

        if (start != null && end != null) {
            shape.left = start!!.x
            shape.top = start!!.y
            shape.right = end!!.x
            shape.bottom = end!!.y
            shape.paint = paint ?: Paint()
        } else {
            throw Exception("条件不充足")
        }

        return shape
    }

    fun reCount() {
        width = right - left
        height = bottom - top
    }
}