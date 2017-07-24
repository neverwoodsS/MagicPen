package com.lab.zhangll.magicpen

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun test() {
        arrayOf("郭靖", "文天祥", "薛仁贵")
                .zip(arrayOf(0, 0, 2))
                .map { it.first.substring(it.second, it.second + 1) }
                .forEach { print(it) }
    }
}