package com.lab.zhangll.magicpen.lib.shapes.line

import android.graphics.Paint
import com.lab.zhangll.magicpen.lib.setting.MagicSetting

/**
 * Created by zhangll on 2017/5/22.
 */
class MagicLineSetting : MagicSetting() {

    var paint: Paint? = null

    override fun product(): MagicLine {
        if (start != null && end != null) {
            return MagicLine(
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