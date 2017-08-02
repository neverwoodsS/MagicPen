package com.lab.zhangll.magicpen.lib

import android.content.Context
import com.lab.zhangll.magicpen.lib.setting.MagicGesture
import com.lab.zhangll.magicpen.lib.setting.MagicSetting
import com.lab.zhangll.magicpen.lib.shapes.MagicShape
import com.lab.zhangll.magicpen.lib.shapes.bitmap.MagicBitmapSetting
import com.lab.zhangll.magicpen.lib.shapes.circle.MagicArcSetting
import com.lab.zhangll.magicpen.lib.shapes.circle.MagicCircleSetting
import com.lab.zhangll.magicpen.lib.shapes.line.MagicLineSetting
import com.lab.zhangll.magicpen.lib.shapes.rect.MagicRectSetting
import com.lab.zhangll.magicpen.lib.shapes.text.MagicTextSetting

/**
 * Created by zhangll on 2017/5/20.
 */
fun Context.magicPen(set: MagicView.() -> Unit) = MagicView(this).apply { set() }

inline fun <reified T : MagicShape, reified R : MagicSetting<T>> MagicView.settingOf(set: R.() -> Unit): R {
    val shapeClazz = T::class.java
    // 1.创建shape对象
    val shape = shapeClazz.getConstructor().newInstance()
    // 2.创建setting对象
    val setting = R::class.java.getConstructor(shapeClazz).newInstance(shape)

    // 3.根据setting对象修改shape
    setting.set()
    addShape(setting.generate(shape))
    return setting
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

fun MagicView.circle(set: MagicCircleSetting.() -> Unit) = settingOf(set)

fun MagicView.text(set: MagicTextSetting.() -> Unit) = settingOf(set)

fun MagicView.line(set: MagicLineSetting.() -> Unit) = settingOf(set)

fun MagicView.rect(set: MagicRectSetting.() -> Unit) = settingOf(set)

fun MagicView.bitmap(set: MagicBitmapSetting.() -> Unit) = settingOf(set)

fun MagicView.arc(set: MagicArcSetting.() -> Unit) = settingOf(set)