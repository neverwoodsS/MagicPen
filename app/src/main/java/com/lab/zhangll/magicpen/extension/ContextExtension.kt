package com.lab.zhangll.magicpen.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by xiasuhuei321 on 2017/7/31.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */
inline fun Context.shortToast(msg: String)
        = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()


inline fun <reified T : Activity> Context.start()
        = startActivity(Intent(this, T::class.java))