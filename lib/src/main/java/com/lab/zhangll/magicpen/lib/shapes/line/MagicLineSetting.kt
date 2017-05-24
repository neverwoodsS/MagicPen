package com.lab.zhangll.magicpen.lib.shapes.line

import android.graphics.Paint
import com.lab.zhangll.magicpen.lib.setting.MagicSetting

/**
 * Created by zhangll on 2017/5/22.
 */
class MagicLineSetting(shape: MagicLine) : MagicSetting<MagicLine>(shape) {

    var paint: Paint? = null

    override fun product(shape: MagicLine): MagicLine {
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
}