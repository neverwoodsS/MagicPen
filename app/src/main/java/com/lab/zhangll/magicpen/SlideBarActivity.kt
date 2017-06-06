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
                            onClick = { button.smoothMoveCenterTo(firstCenterX, centerY) }
                        }
                    }

                    // 圆点
                    circle {
                        radius = 30f
                        center = PointF(secondCenterX, centerY)

                        paint = grayPaint

                        gesture {
                            // 点击时将按钮移动过来
                            onClick = { button.smoothMoveCenterTo(secondCenterX, centerY) }
                        }
                    }

                    // 中间按钮
                    button = bitmap {
                        width = 120f
                        height = 120f
                        center = PointF(centerX, centerY)
                        src = R.mipmap.ic_launcher

                        gesture {
                            onDragBy = { x, _ ->
                                // 在指定范围内跟着手指拖动（仅 X 轴）
                                if ((x + downPoint.x) in firstCenterX..secondCenterX) {
                                    moveBy(x, 0f)
                                }
                            }

                            onRelease = { x, _ ->
                                // 在不同位置放手，移动至不同的位置
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