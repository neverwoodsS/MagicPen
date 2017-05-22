package com.lab.zhangll.magicpen.lib.setting

import android.graphics.PointF
import com.lab.zhangll.magicpen.lib.MagicShape

/**
 * Created by zhangll on 2017/5/20.
 */
abstract class MagicSetting : MagicRelationship {

    /** 用于生成对应图形类的方法，由具体的子类实现 */
    abstract fun product(): MagicShape

    override var start: PointF? = null
    override var end: PointF? = null

    override var width: Float? = null
    override var height: Float? = null

    override var leftMargin = 0f
        set(value) {
            field = value
            start = PointF((start?.x ?: 0f) + value,
                    start?.y ?: 0f)
            end = reEnd()
        }

    override var rightMargin = 0f
        set(value) {
            field = value
            start = PointF((start?.x ?: 0f) - value,
                    start?.y ?: 0f)
            end = reEnd()
        }

    override var topMargin = 0f
        set(value) {
            field = value
            start = PointF(start?.x ?: 0f,
                    (start?.y ?: 0f) + value)
            end = reEnd()
        }

    override var bottomMargin = 0f
        set(value) {
            field = value
            start = PointF(start?.x ?: 0f,
                    (start?.y ?: 0f) - value)
            end = reEnd()
        }
}