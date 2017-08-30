package com.lab.zhangll.magicpen.lib.shapes

import android.graphics.PointF
import android.util.Log
import android.view.View
import com.lab.zhangll.magicpen.lib.base.MagicDraw
import com.lab.zhangll.magicpen.lib.base.MagicGesture
import com.lab.zhangll.magicpen.lib.base.MagicLocation
import com.lab.zhangll.magicpen.lib.base.MagicMotion

/**
 * Created by zhangll on 2017/5/20.
 * Shape 基类，用于整合多个协议的实现
 */
abstract class MagicShape : MagicLocation(), MagicDraw, MagicMotion {
    lateinit var parent: View
    open var gesture: MagicGesture? = null

    open fun reBounds() {
        left = start?.x ?: 0f
        top = start?.y ?: 0f
        right = end?.x ?: 0f
        bottom = end?.y ?: 0f
    }

    override fun invalidate() {
        relations.forEach { it.invoke() }
        parent.invalidate()
    }

    override fun invalidateDirectly() {
        parent.invalidate()
    }

    /**
     *  MagicView将会调用此方法将Shape放置到View中间
     *  由shape决定是否复写实现
     */
    open fun centerHorizontal(centerX: Double) {
        Log.i("MagicLine", "centerX=$centerX " +
                "start=$start end=$end")
        val lineCenter = (start!!.x + end!!.x) / 2.0
        val dis = centerX - lineCenter
        Log.i("MagicLine", "dis=$dis lineCenter=$lineCenter")
        val startX = start!!.x + dis
        val endX = end!!.x + dis
        start = PointF(startX.toFloat(), start!!.y)
        end = PointF(endX.toFloat(), end!!.y)
        Log.i("MagicLine", "start=$start end=$end")
    }

    /**
     *  MagicView将会调用此方法将Shape放置到View中间
     *  由shape决定是否复写实现
     */
    open fun centerVertical(centerY: Double) {
        val lineCenter = (start!!.y + end!!.y) / 2.0
        val dis = centerY - lineCenter
        val startY = start!!.y + dis
        val endY = end!!.y + dis
        start = PointF(start!!.x, startY.toFloat())
        end = PointF(end!!.x, endY.toFloat())
    }
}