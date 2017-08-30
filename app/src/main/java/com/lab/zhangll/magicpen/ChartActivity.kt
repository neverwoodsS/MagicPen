package com.lab.zhangll.magicpen

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lab.zhangll.magicpen.lib.*
import com.lab.zhangll.magicpen.lib.paint.paint

/**
 * Created by zhangll on 2017/8/8.
 */
class ChartActivity : AppCompatActivity() {
    val circleCenter = PointF(700f, 800f)
    val arcRadius = 500f

    // 根据半径和角度计算 point 的公式
    val formula = { angle: Float, radius: Float ->
        val trueAngle = angle * Math.PI / 180
        PointF(circleCenter.x + radius * Math.cos(trueAngle).toFloat(),
                circleCenter.y + radius * Math.sin(trueAngle).toFloat())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
                magicPen {
                    /**
                     * step1
                     * 扇形、边框线、中心圆点
                     */

                    val first = arc {
                        center = circleCenter
                        radius = arcRadius
                        useCenter = true
                        startAngle = -195f
                        sweepAngle = 85f

                        paint = paint {
                            color = Color.RED
                        }
                    }

                    val second = arc {
                        center = circleCenter
                        radius = arcRadius
                        useCenter = true
                        startAngle = first.startAngle + first.sweepAngle
                        sweepAngle = 50f

                        paint = paint {
                            color = Color.YELLOW
                        }
                    }

                    val third = arc {
                        center = circleCenter
                        radius = arcRadius
                        useCenter = true
                        startAngle = second.startAngle + second.sweepAngle
                        sweepAngle = 75f

                        paint = paint {
                            color = Color.GREEN
                        }
                    }

                    arc {
                        center = circleCenter
                        radius = arcRadius
                        startAngle = first.startAngle
                        sweepAngle = third.startAngle + third.sweepAngle - startAngle

                        paint = paint {
                            color = Color.GRAY
                            strokeWidth = 10f
                            style = Paint.Style.STROKE
                        }
                    }

                    val leftLine = line {
                        start = formula.invoke(first.startAngle, arcRadius)
                        end = circleCenter

                        paint = paint {
                            color = Color.GRAY
                            strokeWidth = 10f
                        }
                    }

                    line {
                        start = formula.invoke(third.startAngle + third.sweepAngle, arcRadius)
                        end = circleCenter

                        paint = paint {
                            color = Color.GRAY
                            strokeWidth = 10f
                        }
                    }

                    val centerPoint = circle {
                        center = circleCenter
                        radius = 12f
                        paint = paint {
                            color = Color.WHITE
                        }
                    }


                    /**
                     * step2
                     * 刻度
                     */
                    for (angle in -180..0 step 5) {
                        val temp = line {
                            start = formula.invoke(angle.toFloat(), arcRadius - 8)
                            end = formula.invoke(angle.toFloat(), arcRadius - if (angle % 6 == 0) 50 else 30)

                            paint = paint {
                                color = Color.WHITE
                                strokeWidth = if (angle % 6 == 0) 6f else 4f
                            }
                        }

                        if (angle == -180)
                            text {
                                content = "0%"
                                paint = paint { textSize = 50f }
                                centerIn(temp)
                                leftOf(temp)
                                rightMargin = 20f
                            }

                        if (angle == -90)
                            text {
                                content = "50%"
                                paint = paint { textSize = 50f }
                                centerIn(temp)
                                aboveOf(temp)
                                bottomMargin = 20f
                            }

                        if (angle == 0)
                            text {
                                content = "100%"
                                paint = paint { textSize = 50f }
                                centerIn(temp)
                                rightOf(temp)
                                leftMargin = 20f
                            }

                    }

                    /**
                     * step3
                     * 区间图例
                     */
                    val redRect = rect {
                        width = 50f
                        height = 50f
                        belowOf(leftLine)
                        leftOf(leftLine)
                        topMargin = 30f
                        leftMargin = 30f

                        paint = paint { color = Color.RED }
                    }

                    val redText = text {
                        content = "预警区间"
                        paint = paint { textSize = 50f }
                        centerIn(redRect)
                        rightOf(redRect)
                        leftMargin = 30f
                    }

                    val yellowRect = rect {
                        width = 50f
                        height = 50f
                        alignTop(redRect)
                        rightOf(redText)
                        leftMargin = 60f

                        paint = paint { color = Color.YELLOW }
                    }

                    val yellowText = text {
                        content = "合理区间"
                        paint = paint { textSize = 50f }
                        centerIn(yellowRect)
                        rightOf(yellowRect)
                        leftMargin = 30f
                    }

                    val greenRect = rect {
                        width = 50f
                        height = 50f
                        alignTop(yellowRect)
                        rightOf(yellowText)
                        leftMargin = 60f

                        paint = paint { color = Color.GREEN }
                    }

                    text {
                        content = "优秀区间"
                        paint = paint { textSize = 50f }
                        centerIn(greenRect)
                        rightOf(greenRect)
                        leftMargin = 30f
                    }
                }
        )
    }
}