package com.lab.zhangll.magicpen.lib

import android.content.Context
import com.lab.zhangll.magicpen.lib.setting.MagicSetting
import com.lab.zhangll.magicpen.lib.shapes.MagicCircleSetting

/**
 * Created by zhangll on 2017/5/20.
 */
fun Context.magicPen(set: MagicView.() -> Unit)
        :  MagicView {
    val magicView = MagicView(this)
    magicView.set()
    return magicView
}

fun MagicView.circle(set: MagicCircleSetting.() -> Unit): MagicSetting {
    val setting = MagicCircleSetting().apply { set() }
    shapes.add(setting.product())

    return setting
}