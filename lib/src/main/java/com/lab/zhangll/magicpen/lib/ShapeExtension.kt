package com.lab.zhangll.magicpen.lib

import android.content.Context
import com.lab.zhangll.magicpen.lib.base.MagicGesture
import com.lab.zhangll.magicpen.lib.shapes.*

/**
 * Created by zhangll on 2017/5/20.
 */
fun Context.magicPen(set: MagicView.() -> Unit) = MagicView(this).apply { set() }

inline fun <reified T : MagicShape> MagicView.settingOf(set: T.() -> Unit): T {
    val shapeClazz = T::class.java
    val shape = shapeClazz.getConstructor().newInstance()
    shape.set()

    addShape(shape)
    return shape
}

fun <T : MagicShape> T.gesture(set: MagicGesture.() -> Unit) {
    this.gesture = MagicGesture().apply { set.invoke(this) }
}

fun MagicView.circle(set: MagicCircle.() -> Unit) = settingOf(set)

fun MagicView.text(set: MagicText.() -> Unit) = settingOf(set)

fun MagicView.line(set: MagicLine.() -> Unit) = settingOf(set)

fun MagicView.rect(set: MagicRect.() -> Unit) = settingOf(set)

fun MagicView.bitmap(set: MagicBitmap.() -> Unit) = settingOf(set)

fun MagicView.arc(set: MagicArc.() -> Unit) = settingOf(set)