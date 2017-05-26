package com.lab.zhangll.magicpen.lib.shapes.text

import android.graphics.Paint
import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.setting.MagicSetting

/**
 * Created by zhangll on 2017/5/26.
 */
class MagicTextSetting(shape: MagicText) : MagicSetting<MagicText>(shape) {

    var center: PointF? = null

    var text: String? = null
        set(value) {
            field = value
            recountWidthAndHeight()
        }

    var paint: Paint? = null
        set(value) {
            field = value
            recountWidthAndHeight()
        }

    override fun product(shape: MagicText): MagicText {
        if (paint == null) {
            paint = Paint()
        }

        if (center != null) {
            start = PointF(center!!.x - width!! / 2,
                    center!!.y - height!! / 2)

            end = PointF(center!!.x + width!! / 2,
                    center!!.y + height!! / 2)
        }

        if (start != null && end != null) {
            shape.left = start!!.x
            shape.top = start!!.y
            shape.right = end!!.x
            shape.bottom = end!!.y
            shape.text = text ?: ""
            shape.paint = paint ?: Paint()
        } else {
            throw Exception("条件不充足")
        }

        return shape
    }

    private fun recountWidthAndHeight() {
        if (paint == null) {
            paint = Paint()
        }

        width = paint!!.measureText(text)

        val fontMetrics = paint!!.fontMetrics
//        val yOffSet = fontMetrics.descent - (fontMetrics.descent - fontMetrics.ascent) / 2
        height = fontMetrics.descent + fontMetrics.ascent
    }
}