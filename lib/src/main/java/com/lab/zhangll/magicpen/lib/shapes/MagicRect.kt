package com.lab.zhangll.magicpen.lib.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/6/5.
 */
class MagicRect : MagicShape() {
    var center = PointF(0f, 0f)
        set(value) {
            field = value
            reLocate()
        }

//    override var width: Float = 0f
//        set(value) {
//            field = value
//            reLocate()
//        }
//
//    override var height: Float = 0f
//        set(value) {
//            field = value
//            reLocate()
//        }

    override var paint: Paint = Paint()

    override fun containPoint(x: Float, y: Float) = containInRect(x, y)

    override fun drawOn(canvas: Canvas?) = canvas?.drawRect(left, top, right, bottom, paint)

    fun reLocate() {
        start = PointF(center.x - width / 2, center.y - height / 2)
        end = PointF(center.x + width / 2, center.y + height / 2)
    }

    override fun reBounds() {
        reLocate()
        super.reBounds()
    }
}