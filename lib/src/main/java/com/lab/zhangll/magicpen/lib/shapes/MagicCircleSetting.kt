package com.lab.zhangll.magicpen.lib.shapes

import android.graphics.Paint
import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.setting.MagicSetting

/**
 * Created by zhangll on 2017/5/22.
 */
class MagicCircleSetting : MagicSetting() {
    var center: PointF? = null
    var radius: Float? = null
        set(value) {
            if (value != null) {
                width = value * 2
                height = value * 2
            }
            field = value
        }

    var paint: Paint? = null

    fun product(): MagicCircle {
        if (center != null && radius != null) {
            start = PointF(center!!.x - radius!!, center!!.y - radius!!)
            end = PointF(center!!.x + radius!!, center!!.y + radius!!)
        }

        if (start != null && end != null) {
            return MagicCircle(
                    start!!.x,
                    start!!.y,
                    end!!.x,
                    end!!.y,
                    paint ?: Paint())
        } else {
            throw Exception("条件不充足")
        }
    }
}