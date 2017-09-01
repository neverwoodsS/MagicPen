package com.lab.zhangll.magicpen.lib.base

import android.graphics.PointF
import android.util.Log

/**
 * Created by zhangll on 2017/5/20.
 * 描述 Shape 位置相关属性
 * start、end 用于描述 Shape 应在位置
 * left、top、right、bottom 用于描述实际绘制位置
 */
abstract class MagicLocation : MagicRelationship() {
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

    override var width = 0f
    override var height = 0f

    override var left = 0f
    override var top = 0f
    override var right = 0f
    override var bottom = 0f

    abstract fun containPoint(x: Float, y: Float): Boolean
    fun containInRect(x: Float, y: Float) = x in left..right && y in top..bottom
}