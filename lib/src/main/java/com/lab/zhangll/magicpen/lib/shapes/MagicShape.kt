package com.lab.zhangll.magicpen.lib.shapes

import android.animation.ValueAnimator
import android.graphics.PointF
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.lab.zhangll.magicpen.lib.setting.MagicMotion
import com.lab.zhangll.magicpen.lib.setting.MagicGesture
import com.lab.zhangll.magicpen.lib.setting.MagicRelationship

/**
 * Created by zhangll on 2017/5/20.
 */
abstract class MagicShape : MagicDraw, MagicMotion, MagicRelationship {

    override var left: Float = 0f
    override var top: Float = 0f
    override var right: Float = 0f
    override var bottom: Float = 0f

    override var start: PointF? = null
        set(value) {
            field = value
            left = value?.x ?: 0f
            top = value?.y ?: 0f
        }

    override var end: PointF? = null
        set(value) {
            field = value
            right = value?.x ?: 0f
            bottom = value?.y ?: 0f
        }

    override var width: Float = 0f
    override var height: Float = 0f

    override var leftMargin = 0f
        set(value) {
            field = value
            val func = {
                start = PointF((start?.x ?: 0f) + value,
                        start?.y ?: 0f)
                end = reEnd()
            }
            func.invoke()
            relations.add(func)
        }

    override var rightMargin = 0f
        set(value) {
            field = value
            val func = {
                start = PointF((start?.x ?: 0f) - value,
                        start?.y ?: 0f)
                end = reEnd()
            }
            func.invoke()
            relations.add(func)
        }

    override var topMargin = 0f
        set(value) {
            field = value
            val func = {
                start = PointF(start?.x ?: 0f,
                        (start?.y ?: 0f) + value)
                end = reEnd()
            }
            func.invoke()
            relations.add(func)
        }

    override var bottomMargin = 0f
        set(value) {
            field = value
            val func = {
                start = PointF(start?.x ?: 0f,
                        (start?.y ?: 0f) - value)
                end = reEnd()
            }
            func.invoke()
            relations.add(func)
        }

    lateinit var parent: View
    open var gesture: MagicGesture? = null
    override val relations: MutableList<() -> Unit> = mutableListOf()

    abstract fun containPoint(x: Float, y: Float): Boolean

    override fun moveBy(x: Float, y: Float) {
        left = start!!.x + x
        top = start!!.y + y
        right = end!!.x + x
        bottom = end!!.y + y

        parent.invalidate()
    }

    override fun moveToOrigin() {
        left = start!!.x
        top = start!!.y
        right = end!!.x
        bottom = end!!.y

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

        start!!.x = targetX
        start!!.y = targetY
        end!!.x = start!!.x + width
        end!!.y = start!!.y + height
    }

    override fun smoothMoveCenterTo(targetX: Float, targetY: Float) {
        smoothMoveTo(targetX - width / 2, targetY - height / 2)
    }

    override fun smoothMoveToOrigin() {
        smoothMoveTo(start!!.x, start!!.y)
    }

    fun containInRect(x: Float, y: Float): Boolean {
        return x in left..right
                && y in top..bottom
    }

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
}