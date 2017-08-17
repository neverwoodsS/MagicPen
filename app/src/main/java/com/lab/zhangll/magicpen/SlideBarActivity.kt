package com.lab.zhangll.magicpen

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lab.zhangll.magicpen.lib.*
import com.lab.zhangll.magicpen.lib.shapes.MagicBitmap

class SlideBarActivity : AppCompatActivity() {

    val firstCenterX = 100f // 左圆心
    val secondCenterX = 900f // 右圆心

    val centerX = (firstCenterX + secondCenterX) / 2 // 中心 X
    val centerY = 200f // 中心 Y
    val grayPaint = Paint().apply { color = Color.parseColor("#ebebeb") }

    lateinit var button: MagicBitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MagicPen.init(application)
        setContentView(
                magicPen {
                    setBackgroundColor(Color.WHITE)
                    rect { // 线条
                        left = firstCenterX
                        right = secondCenterX
                        top = centerY - 5
                        bottom = centerY + 5

                        paint = grayPaint
                    }

                    circle { // 圆点
                        radius = 30f
                        center = PointF(firstCenterX, centerY)

                        paint = grayPaint

                        gesture {
                            onClick = { // 点击时将按钮移动过来
                                button.src = R.mipmap.slide_button_left_normal
                                button.smoothMoveCenterTo(firstCenterX, centerY)
                            }
                        }
                    }

                    circle { // 圆点
                        radius = 30f
                        center = PointF(secondCenterX, centerY)

                        paint = grayPaint

                        gesture {
                            onClick = { // 点击时将按钮移动过来
                                button.src = R.mipmap.slide_button_right_normal
                                button.smoothMoveCenterTo(secondCenterX, centerY)
                            }
                        }
                    }

                    button = bitmap { // 中间按钮
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
                                val targetX: Float // 在不同位置放手，移动至不同的位置
                                when(x) {
                                    in 0f..divideX1 -> {
                                        targetX = firstCenterX
                                        src = R.mipmap.slide_button_left_normal
                                    }
                                    in divideX2..Float.MAX_VALUE -> {
                                        targetX = secondCenterX
                                        src = R.mipmap.slide_button_right_normal
                                    }
                                    else -> {
                                        targetX = centerX
                                        src = R.mipmap.slide_button_center_normal
                                    }
                                }

                                smoothMoveCenterTo(targetX, centerY)
                            }
                        }
                    }
                }
        )
    }
}