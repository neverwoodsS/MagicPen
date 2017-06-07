package com.lab.zhangll.magicpen

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lab.zhangll.magicpen.lib.*
import com.lab.zhangll.magicpen.lib.shapes.bitmap.MagicBitmapSetting

class SlideBarActivity : AppCompatActivity() {

    val firstCenterX = 100f
    val secondCenterX = 900f

    val centerX = (firstCenterX + secondCenterX) / 2
    val centerY = 200f
    val grayPaint = Paint().apply { color = Color.parseColor("#ebebeb") }

    lateinit var button: MagicBitmapSetting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MagicPen.init(application)
        setContentView(
                magicPen {
                    // 线条
                    rect {
                        left = firstCenterX
                        right = secondCenterX
                        top = centerY - 5
                        bottom = centerY + 5

                        paint = grayPaint
                    }

                    // 圆点
                    circle {
                        radius = 30f
                        center = PointF(firstCenterX, centerY)

                        paint = grayPaint

                        gesture {
                            // 点击时将按钮移动过来
                            onClick = {
                                button.src = R.mipmap.slide_button_left_normal
                                button.smoothMoveCenterTo(firstCenterX, centerY)
                            }
                        }
                    }

                    // 圆点
                    circle {
                        radius = 30f
                        center = PointF(secondCenterX, centerY)

                        paint = grayPaint

                        gesture {
                            // 点击时将按钮移动过来
                            onClick = {
                                button.src = R.mipmap.slide_button_right_normal
                                button.smoothMoveCenterTo(secondCenterX, centerY)
                            }
                        }
                    }

                    // 中间按钮
                    button = bitmap {
                        val divideX1 = centerX - 150 // left  区域和 center 区域的交接点
                        val divideX2 = centerX + 150 // right 区域和 center 区域的交接点

                        width = 120f
                        height = 120f
                        center = PointF(centerX, centerY)
                        src = R.mipmap.slide_button_center_normal // file = "..."

                        gesture {
                            onDragBy = { x, _ ->
                                val targetX = x + downPoint.x
                                if (targetX in firstCenterX..secondCenterX) { // 在指定范围内跟着手指拖动（仅 X 轴）
                                    src = when(targetX) { // 根据不同的位置，加载不同的资源
                                        in 0f..divideX1 -> R.mipmap.slide_button_left_pressed
                                        in divideX2..Float.MAX_VALUE -> R.mipmap.slide_button_right_pressed
                                        else -> R.mipmap.slide_button_center_pressed
                                    }

                                    moveBy(x, 0f)
                                }
                            }

                            onRelease = { x, _ ->
                                // 在不同位置放手，移动至不同的位置
                                val target: Float
                                when(x) {
                                    in 0f..divideX1 -> {
                                        target = firstCenterX
                                        src = R.mipmap.slide_button_left_normal
                                    }
                                    in divideX2..Float.MAX_VALUE -> {
                                        target = secondCenterX
                                        src = R.mipmap.slide_button_right_normal
                                    }
                                    else -> {
                                        target = centerX
                                        src = R.mipmap.slide_button_center_normal
                                    }
                                }

                                smoothMoveCenterTo(target, centerY)
                            }
                        }
                    }
                }
        )
    }
}