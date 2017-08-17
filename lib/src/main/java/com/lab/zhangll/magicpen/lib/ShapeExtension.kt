package com.lab.zhangll.magicpen.lib

import android.content.Context
import com.lab.zhangll.magicpen.lib.setting.MagicGesture
import com.lab.zhangll.magicpen.lib.shapes.MagicShape
import com.lab.zhangll.magicpen.lib.shapes.arc.MagicArc
import com.lab.zhangll.magicpen.lib.shapes.bitmap.MagicBitmap
import com.lab.zhangll.magicpen.lib.shapes.circle.MagicCircle
import com.lab.zhangll.magicpen.lib.shapes.line.MagicLine
import com.lab.zhangll.magicpen.lib.shapes.rect.MagicRect
import com.lab.zhangll.magicpen.lib.shapes.text.MagicText

/**
 * Created by zhangll on 2017/5/20.
 */
fun Context.magicPen(set: MagicView.() -> Unit) = MagicView(this).apply { set() }

inline fun <reified T : MagicShape> MagicView.settingOf(set: T.() -> Unit): T {
    val shapeClazz = T::class.java
    val shape = shapeClazz.getConstructor().newInstance()
    shape.parent = this
    shape.set()

    addShape(shape)
    return shape
}

fun <T : MagicShape> T.gesture(set: MagicGesture.() -> Unit) {
    this.gesture = MagicGesture().apply { set.invoke(this) }
}

//fun <T : MagicShape> T.generate(shape: T): T {
//    val productShape = product(shape)
//
//    if (gestureSet != null) {
//        gesture = MagicGesture()
//        gestureSet!!.invoke(gesture!!)
//    }
//
//    productShape.gesture = gesture
//    return productShape
//}

fun MagicView.circle(set: MagicCircle.() -> Unit) = settingOf(set)

fun MagicView.text(set: MagicText.() -> Unit) = settingOf(set)

fun MagicView.line(set: MagicLine.() -> Unit) = settingOf(set)

fun MagicView.rect(set: MagicRect.() -> Unit) = settingOf(set)

fun MagicView.bitmap(set: MagicBitmap.() -> Unit) = settingOf(set)

fun MagicView.arc(set: MagicArc.() -> Unit) = settingOf(set)