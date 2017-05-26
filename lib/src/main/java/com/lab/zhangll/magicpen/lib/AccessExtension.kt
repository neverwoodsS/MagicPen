package com.lab.zhangll.magicpen.lib

import android.content.Context
import com.lab.zhangll.magicpen.lib.setting.MagicGesture
import com.lab.zhangll.magicpen.lib.setting.MagicSetting
import com.lab.zhangll.magicpen.lib.shapes.MagicShape
import com.lab.zhangll.magicpen.lib.shapes.circle.MagicCircle
import com.lab.zhangll.magicpen.lib.shapes.circle.MagicCircleSetting
import com.lab.zhangll.magicpen.lib.shapes.line.MagicLine
import com.lab.zhangll.magicpen.lib.shapes.line.MagicLineSetting

/**
 * Created by zhangll on 2017/5/20.
 */
fun Context.magicPen(set: MagicView.() -> Unit)
        :  MagicView {
    val magicView = MagicView(this)
    magicView.set()
    return magicView
}

fun <T : MagicShape> MagicSetting<T>.gesture(set: MagicGesture.() -> Unit) {
    this.gesture = MagicGesture().apply { set.invoke(this) }
}

fun <T : MagicShape> MagicSetting<T>.generate(shape: T): T {
    val productShape = product(shape)

    if (gestureSet != null) {
        gesture = MagicGesture()
        gestureSet!!.invoke(gesture!!)
    }

    productShape.gesture = gesture
    return productShape
}

fun MagicView.circle(set: MagicCircleSetting.() -> Unit): MagicSetting<MagicCircle> {
    val shape = MagicCircle()

    val setting = MagicCircleSetting(shape).apply { set() }
    addShape(setting.generate(shape))

    return setting
}

fun MagicView.line(set: MagicLineSetting.() -> Unit): MagicSetting<MagicLine> {
    val shape = MagicLine()

    val setting = MagicLineSetting(shape).apply { set() }
    addShape(setting.generate(shape))

    return setting
}