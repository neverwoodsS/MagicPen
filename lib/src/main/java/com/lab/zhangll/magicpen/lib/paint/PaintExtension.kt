package com.lab.zhangll.magicpen.lib.paint

import android.content.Context
import android.graphics.Paint

/**
 * Created by zhangll on 2017/7/14.
 */
fun Context.paint(setting: Paint.() -> Unit): Paint {
    return Paint().apply {
        isAntiAlias = true
        setting()
    }
}