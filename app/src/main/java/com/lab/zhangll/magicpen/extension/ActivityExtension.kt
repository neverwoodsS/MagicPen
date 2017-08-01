package com.lab.zhangll.magicpen.extension

import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by xiasuhuei321 on 2017/7/31.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */
fun <T: View> AppCompatActivity.find(id: Int): T {
    return findViewById(id) as T
}