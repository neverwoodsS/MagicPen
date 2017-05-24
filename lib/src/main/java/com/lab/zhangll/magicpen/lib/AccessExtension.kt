package com.lab.zhangll.magicpen.lib

import android.content.Context
import com.lab.zhangll.magicpen.lib.setting.MagiGestureResponder
import com.lab.zhangll.magicpen.lib.setting.MagicGesture
import com.lab.zhangll.magicpen.lib.setting.MagicSetting
import com.lab.zhangll.magicpen.lib.shapes.MagicShape
import com.lab.zhangll.magicpen.lib.shapes.circle.MagicCircleSetting
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

fun MagicSetting.gesture(set: MagicGesture.() -> Unit) {
    this.gestureSet = set
}

fun MagicSetting.generate(): MagicShape {
    val shape = product()

    if (gestureSet != null) {
        gesture = MagicGesture(shape)
        gestureSet!!.invoke(gesture!!)
    }

    shape.gesture = gesture
    return shape
}

fun MagicView.circle(set: MagicCircleSetting.() -> Unit): MagicSetting {
    val setting = MagicCircleSetting().apply { set() }
    shapes.add(setting.generate())

    return setting
}

fun MagicView.line(set: MagicLineSetting.() -> Unit): MagicSetting {
    val setting = MagicLineSetting().apply { set() }
    shapes.add(setting.generate())

    return setting
}