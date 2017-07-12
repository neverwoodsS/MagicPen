package com.lab.zhangll.magicpen.lib.shapes.circle

import android.graphics.Paint
import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.setting.MagicSetting
import com.lab.zhangll.magicpen.lib.shapes.arc.MagicArc

/**
 * Created by zhangll on 2017/5/22.
 */
class MagicArcSetting(shape: MagicArc) : MagicSetting<MagicArc>(shape) {
    var center: PointF? = null
    var radius: Float? = null
        set(value) {
            if (value != null) {
                width = value * 2
                height = value * 2
            }
            field = value
        }

    var startAngle = 0f
    var sweepAngle = 0f

    var paint: Paint? = null

    override fun product(shape: MagicArc): MagicArc {
        if (center != null && radius != null) {
            start = PointF(center!!.x - radius!!, center!!.y - radius!!)
            end = PointF(center!!.x + radius!!, center!!.y + radius!!)
        }

        if (start != null && end != null) {
            shape.left = start!!.x
            shape.top = start!!.y
            shape.right = end!!.x
            shape.bottom = end!!.y
            shape.paint = paint ?: Paint()
            shape.startAngle = startAngle
            shape.sweepAngle = sweepAngle
        } else {
            throw Exception("条件不充足")
        }

        return shape
    }
}