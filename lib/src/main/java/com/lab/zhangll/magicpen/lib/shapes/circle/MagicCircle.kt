package com.lab.zhangll.magicpen.lib.shapes.circle

import com.lab.zhangll.magicpen.lib.MagicShape
import com.lab.zhangll.magicpen.lib.centerX
import com.lab.zhangll.magicpen.lib.centerY
import com.lab.zhangll.magicpen.lib.width

/**
 * Created by zhangll on 2017/5/20.
 */
class MagicCircle(
        override var left: Float,
        override var top: Float,
        override var right: Float,
        override var bottom: Float,
        override var paint: android.graphics.Paint
) : MagicShape {
    override fun drawOn(canvas: android.graphics.Canvas?) = canvas?.drawCircle(centerX, centerY, width / 2, paint)
}