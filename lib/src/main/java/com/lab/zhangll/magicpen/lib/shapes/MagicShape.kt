package com.lab.zhangll.magicpen.lib.shapes

import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.setting.MagiGestureResponder
import com.lab.zhangll.magicpen.lib.setting.MagicGesture

/**
 * Created by zhangll on 2017/5/20.
 */
abstract class MagicShape : MagicLocation, MagicDraw, MagiGestureResponder {
    open var gesture: MagicGesture? = null

    val start: PointF by lazy { PointF(left, top) }
    val end: PointF by lazy { PointF(right, bottom) }

    abstract fun containPoint(x: Float, y: Float): Boolean

    override fun moveBy(x: Float, y: Float) {
        left = start.x + x
        top = start.y + y
        right = end.x + x
        bottom = end.y + y
    }

    override fun moveToOrigin() {
        left = start.x
        top = start.y
        right = end.x
        bottom = end.y
    }
}

fun MagicShape.containInRect(x: Float, y: Float): Boolean {
    return x in left..right
            && y in top..bottom
}