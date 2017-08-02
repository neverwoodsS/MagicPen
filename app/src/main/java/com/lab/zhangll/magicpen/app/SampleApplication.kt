package com.lab.zhangll.magicpen.app

import android.app.Application
import com.lab.zhangll.magicpen.lib.MagicPen

/**
 * Created by xiasuhuei321 on 2017/8/2.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */
class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MagicPen.init(this)
    }
}