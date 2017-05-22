package com.lab.zhangll.magicpen

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lab.zhangll.magicpen.lib.*
import com.lab.zhangll.magicpen.lib.setting.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                magicPen {
                    val bigOne = circle {
                        center = PointF(400f, 500f)
                        radius = 200f

                        paint = Paint().apply { color = Color.RED }
                    }

                    circle {
                        radius = 50f

                        /**
                         * leftOf
                         * rightOf
                         * belowOf
                         * aboveOf
                         *
                         * alignTop
                         * alignBottom
                         * alignLeft
                         * alignRight
                         */

                        alignRight(bigOne)
                        alignBottom(bigOne)

                        rightMargin = 30f
                        bottomMargin = 120f
                    }
                }
        )
    }
}
