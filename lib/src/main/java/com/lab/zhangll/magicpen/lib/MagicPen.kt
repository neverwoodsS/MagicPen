package com.lab.zhangll.magicpen.lib

import android.app.Application

/**
 * Created by zhangll on 2017/6/6.
 */
object MagicPen {
    lateinit var application: Application
        private set

    fun init(application: Application) {
        this.application = application
    }
}