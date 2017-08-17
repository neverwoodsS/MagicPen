package com.lab.zhangll.magicpen

import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.lab.zhangll.magicpen.lib.arc
import com.lab.zhangll.magicpen.lib.magicPen
import com.lab.zhangll.magicpen.lib.paint.paint
import com.lab.zhangll.magicpen.lib.paint.shader.sweepGradient
import com.lab.zhangll.magicpen.lib.shapes.MagicArc

class ArcActivity : AppCompatActivity() {

    lateinit var progress: MagicArc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
                magicPen {
                    setBackgroundColor(Color.BLACK)
                    arc {
                        center = PointF(500f, 500f)
                        radius = 400f

                        startAngle = -210f
                        sweepAngle = 240f

                        paint = paint {
                            strokeWidth = 30f
                            style = Paint.Style.STROKE
                            strokeCap = Paint.Cap.ROUND

                            shader = sweepGradient {
                                centerX = 500f
                                centerY = 500f
                                color = arrayOf(Color.parseColor("#11ffffff"), Color.parseColor("#55ffffff"), Color.parseColor("#11ffffff"))
                                percent = arrayOf(0f, 0.33f, 0.66f)
                                matrix = Matrix().apply {
                                    setRotate(-220f, 500f, 500f)
                                }
                            }
                        }
                    }

                    progress = arc {
                        center = PointF(500f, 500f)
                        radius = 400f

                        startAngle = -210f
                        sweepAngle = 0f

                        paint = paint {
                            strokeWidth = 30f
                            style = Paint.Style.STROKE
                            strokeCap = Paint.Cap.ROUND

                            shader = sweepGradient {
                                centerX = 500f
                                centerY = 500f
                                color = arrayOf(Color.parseColor("#00ffffff"), Color.parseColor("#D9B262"))
                                percent = arrayOf(0f, 240 / 360f)
                                matrix = Matrix().apply {
                                    setRotate(-220f, 500f, 500f)
                                }
                            }
                        }
                    }
                }
        )

        object: CountDownTimer(5000, 25) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                progressing()
            }
        }.start()
    }

    private fun progressing() = with(progress) {
        sweepAngle += (240f / 200)
        invalidate()
    }
}