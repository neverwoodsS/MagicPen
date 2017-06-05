package com.lab.zhangll.magicpen

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lab.zhangll.magicpen.lib.circle
import com.lab.zhangll.magicpen.lib.gesture
import com.lab.zhangll.magicpen.lib.magicPen
import com.lab.zhangll.magicpen.lib.rect

class SlideBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                magicPen {
                    val firstCenterX = 200f
                    val secondCenterX = 1000f

                    val centerX = (firstCenterX + secondCenterX) / 2
                    val centerY = 200f
                    val grayPaint = Paint().apply { color = Color.parseColor("#ebebeb") }

                    circle {
                        radius = 21f
                        center = PointF(firstCenterX, centerY)

                        paint = grayPaint
                    }

                    circle {
                        radius = 21f
                        center = PointF(secondCenterX, centerY)

                        paint = grayPaint
                    }

                    rect {
                        left = firstCenterX
                        right = secondCenterX
                        top = centerY - 5
                        bottom = centerY + 5

                        paint = grayPaint
                    }

                    circle {
                        radius = 60f
                        center = PointF(centerX, centerY)

                        gesture {
                            onDragBy = { x, _ ->
                                when(x + downPoint.x) {
                                    in firstCenterX..secondCenterX -> {
                                        moveBy(x, 0f)
                                    }
                                }
                            }

                            onRelease = { x, _ ->
                                val targetX = when(x) {
                                    in firstCenterX..(centerX - 100) -> firstCenterX
                                    in (centerX + 100)..secondCenterX -> secondCenterX
                                    else -> centerX
                                }
                                smoothMoveCenterTo(targetX, centerY)
                            }
                        }
                    }
                }
        )
    }
}
