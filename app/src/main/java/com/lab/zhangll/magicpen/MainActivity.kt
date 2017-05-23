package com.lab.zhangll.magicpen

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lab.zhangll.magicpen.lib.*
import com.lab.zhangll.magicpen.lib.setting.*

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

//                        alignRight(bigOne)
//                        alignBottom(bigOne)
//                        rightMargin = 30f
//                        bottomMargin = 120f

                        centerIn(bigOne)

                        gesture {
                            onClick = {
                                Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
                            }

                            onDragBy = { x, y ->
                                moveBy(x, y)
                                invalidate()
                            }

                            onRelease = {
                                moveToOrigin()
                                invalidate()
                            }
                        }
                    }
                }
        )
    }
}
