package com.lab.zhangll.magicpen.lib

import android.content.Context
import android.util.TypedValue

/**
 * Created by xiasuhuei321 on 2017/8/29.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 *
 * desc:provide an extension for user convert dp value
 * to px value
 *
 */

fun Context.dp(dpVal: Float): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, this.resources.displayMetrics)

fun Context.dp(dpVal: Int): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal.toFloat(), this.resources.displayMetrics)

fun Context.dp(dpVal: Double): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal.toFloat(), this.resources.displayMetrics)