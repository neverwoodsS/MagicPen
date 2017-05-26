package com.lab.zhangll.magicpen

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lab.zhangll.magicpen.lib.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                magicPen {
                    val line = line {
                        start = PointF(0f, 1000f)
                        end = PointF(1000f, 1000f)
                    }

                    val bigOne = circle {
                        radius = 200f

                        aboveOf(line)
                        leftMargin = 500f
                        bottomMargin = 10f

                        paint = Paint().apply { color = Color.RED }
                    }

                    circle {
                        radius = 50f
                        centerIn(bigOne)

                        gesture {
                            onClick = { println("clicked") }
                            onDragBy = { x, y -> moveBy(x, y) }
                            onRelease = { moveToOrigin() }
                        }
                    }
                }
        )
    }
}
