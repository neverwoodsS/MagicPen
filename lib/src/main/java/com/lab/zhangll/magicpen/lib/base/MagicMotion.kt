package com.lab.zhangll.magicpen.lib.base

/**
 * Created by zhangll on 2017/5/23.
 * Shape 的基本运动
 */
interface MagicMotion : com.lab.zhangll.magicpen.lib.base.MagicLocation {

    fun invalidate()
    fun invalidateDirectly()

    fun moveBy(x: Float, y: Float) {
        left = start!!.x + x
        top = start!!.y + y
        right = end!!.x + x
        bottom = end!!.y + y

        invalidateDirectly()
    }

    fun moveToOrigin() {
        left = start!!.x
        top = start!!.y
        right = end!!.x
        bottom = end!!.y

        invalidateDirectly()
    }

    fun smoothMoveTo(targetX: Float, targetY: Float) {
        val totalX = targetX - left
        val totalY = targetY - top

        if (totalX == 0f) {
            android.animation.ValueAnimator.ofFloat(totalY, 0f).apply {
                this.duration = 300
                this.interpolator = android.view.animation.AccelerateDecelerateInterpolator()

                addUpdateListener {
                    val dy = it.animatedValue as Float
                    val height = bottom - top

                    top = targetY - dy
                    bottom = targetY + height - dy

                    invalidateDirectly()
                }
            }.start()
        } else {
            android.animation.ValueAnimator.ofFloat(totalX, 0f).apply {
                this.duration = 300
                this.interpolator = android.view.animation.AccelerateDecelerateInterpolator()

                addUpdateListener {
                    val dx = it.animatedValue as Float
                    val dy = dx * totalY / totalX
                    val width = right - left
                    val height = bottom - top

                    left = targetX -  dx
                    right = targetX + width - dx
                    top = targetY - dy
                    bottom = targetY + height - dy

                    invalidateDirectly()
                }
            }.start()
        }

        start!!.x = targetX
        start!!.y = targetY
        end!!.x = start!!.x + width
        end!!.y = start!!.y + height
    }

    fun smoothMoveCenterTo(targetX: Float, targetY: Float) {
        smoothMoveTo(targetX - width / 2, targetY - height / 2)
    }

    fun smoothMoveToOrigin() {
        smoothMoveTo(start!!.x, start!!.y)
    }
}