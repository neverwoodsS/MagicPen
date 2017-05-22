package com.lab.zhangll.magicpen.lib.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.MagicShape
import com.lab.zhangll.magicpen.lib.centerX
import com.lab.zhangll.magicpen.lib.centerY
import com.lab.zhangll.magicpen.lib.setting.MagicSetting
import com.lab.zhangll.magicpen.lib.width

/**
 * Created by zhangll on 2017/5/20.
 */
class MagicCircle(
        override var left: Float,
        override var top: Float,
        override var right: Float,
        override var bottom: Float,
        override var paint: Paint
) : MagicShape {
    override fun drawOn(canvas: Canvas?) {
        canvas?.drawCircle(centerX, centerY, width / 2, paint)
    }
}

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