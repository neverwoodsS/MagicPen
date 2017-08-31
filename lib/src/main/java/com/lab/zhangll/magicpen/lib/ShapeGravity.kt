package com.lab.zhangll.magicpen.lib

/**
 * Created by xiasuhuei321 on 2017/8/31.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */
class ShapeGravity {
    /**
     * all legal combination:
     * START | TOP = 0xffff0000
     * START | BOTTOM = 0xff00ff00
     * END | TOP = 0x00ff00ff
     * END | BOTTOM = 0x0000ffff
     */
    companion object {
        val START: Long = 0xff000000
        val END: Long = 0x000000ff
        val TOP: Long = 0x00ff0000
        val BOTTOM: Long = 0x0000ff00
        val NULL: Long = 0x00000000
    }
}