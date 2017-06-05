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
import com.lab.zhangll.magicpen.lib.shapes.circle.MagicCircleSetting

class SlideBarActivity : AppCompatActivity() {

    val firstCenterX = 200f
    val secondCenterX = 1000f

    val centerX = (firstCenterX + secondCenterX) / 2
    val centerY = 200f
    val grayPaint = Paint().apply { color = Color.parseColor("#ebebeb") }

    var button: MagicCircleSetting? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                magicPen {
                    rect {
                        left = firstCenterX
                        right = secondCenterX
                        top = centerY - 5
                        bottom = centerY + 5

                        paint = grayPaint
                    }

                    circle {
                        radius = 21f
                        center = PointF(firstCenterX, centerY)

                        paint = grayPaint

                        gesture {
                            onClick = { button?.smoothMoveCenterTo(firstCenterX, centerY) }
                        }
                    }

                    circle {
                        radius = 21f
                        center = PointF(secondCenterX, centerY)

                        paint = grayPaint

                        gesture {
                            onClick = { button?.smoothMoveCenterTo(secondCenterX, centerY) }
                        }
                    }

                    button = circle {
                        radius = 60f
                        center = PointF(centerX, centerY)

                        gesture {
                            onDragBy = { x, _ ->
                                if ((x + downPoint.x) in firstCenterX..secondCenterX) {
                                    moveBy(x, 0f)
                                }
                            }

                            onRelease = { x, _ ->
                                val targetX = when(x) {
                                    in 0f..(centerX - 200) -> firstCenterX
                                    in (centerX + 200)..Float.MAX_VALUE -> secondCenterX
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
