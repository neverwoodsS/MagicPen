package com.lab.zhangll.magicpen.lib.shapes

import android.animation.ValueAnimator
import android.graphics.PointF
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.lab.zhangll.magicpen.lib.setting.MagicMotion
import com.lab.zhangll.magicpen.lib.setting.MagicGesture

/**
 * Created by zhangll on 2017/5/20.
 */
abstract class MagicShape : MagicLocation, MagicDraw, MagicMotion {

    lateinit var parent: View
    open var gesture: MagicGesture? = null

    val start: PointF by lazy { PointF(left, top) }
    val end: PointF by lazy { PointF(right, bottom) }

    abstract fun containPoint(x: Float, y: Float): Boolean

    override fun moveBy(x: Float, y: Float) {
        left = start.x + x
        top = start.y + y
        right = end.x + x
        bottom = end.y + y

        parent.invalidate()
    }

    override fun moveToOrigin() {
        left = start.x
        top = start.y
        right = end.x
        bottom = end.y

        parent.invalidate()
    }

    override fun smoothMoveTo(targetX: Float, targetY: Float) {
        val totalX = targetX - left
        val totalY = targetY - top

        if (totalX == 0f) {
            ValueAnimator.ofFloat(totalY, 0f).apply {
                this.duration = 300
                this.interpolator = AccelerateDecelerateInterpolator()

                addUpdateListener {
                    val dy = it.animatedValue as Float
                    val height = bottom - top

                    top = targetY - dy
                    bottom = targetY + height - dy

                    parent.invalidate()
                }
            }.start()
        } else {
            ValueAnimator.ofFloat(totalX, 0f).apply {
                this.duration = 300
                this.interpolator = AccelerateDecelerateInterpolator()

                addUpdateListener {
                    val dx = it.animatedValue as Float
                    val dy = dx * totalY / totalX
                    val width = right - left
                    val height = bottom - top

                    left = targetX -  dx
                    right = targetX + width - dx
                    top = targetY - dy
                    bottom = targetY + height - dy

                    parent.invalidate()
                }
            }.start()
        }

        start.x = targetX
        start.y = targetY
        end.x = start.x + width
        end.y = start.y + height
    }

    override fun smoothMoveCenterTo(targetX: Float, targetY: Float) {
        smoothMoveTo(targetX - width / 2, targetY - height / 2)
    }

    override fun smoothMoveToOrigin() {
        smoothMoveTo(start.x, start.y)
    }

    fun containInRect(x: Float, y: Float): Boolean {
        return x in left..right
                && y in top..bottom
    }
}