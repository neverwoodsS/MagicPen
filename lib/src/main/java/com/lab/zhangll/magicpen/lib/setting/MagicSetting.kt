package com.lab.zhangll.magicpen.lib.setting

import android.graphics.PointF

/**
 * Created by zhangll on 2017/5/20.
 */
open class MagicSetting : MagicRelationship {
    override var start: PointF? = null
    override var end: PointF? = null

    override var width: Float? = null
    override var height: Float? = null
}