package com.lab.zhangll.magicpen

import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lab.zhangll.magicpen.lib.arc
import com.lab.zhangll.magicpen.lib.circle
import com.lab.zhangll.magicpen.lib.magicPen

class ArcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val matrix = Matrix().apply {
            setRotate(-220f, 500f, 500f)
        }

        val backSG = SweepGradient(500f, 500f,
                arrayOf(Color.parseColor("#11ffffff"), Color.parseColor("#55ffffff"), Color.parseColor("#11ffffff")).toIntArray(),
                arrayOf(0f, 0.33f, 0.66f).toFloatArray())
        backSG.setLocalMatrix(matrix)

        val progressSG = SweepGradient(500f, 500f,
                arrayOf(Color.parseColor("#00ffffff"), Color.parseColor("#D9B262")).toIntArray(),
                arrayOf(0f, 120 / 240f).toFloatArray())
        progressSG.setLocalMatrix(matrix)

        setContentView(
                magicPen {
                    setBackgroundColor(Color.BLACK)
                    arc {
                        center = PointF(500f, 500f)
                        radius = 400f

                        startAngle = -210f
                        sweepAngle = 240f

                        paint = Paint().apply {
                            style = Paint.Style.STROKE
                            strokeWidth = 30f
                            isAntiAlias = true
                            shader = backSG
                            strokeCap = Paint.Cap.ROUND
                        }
                    }

                    arc {
                        center = PointF(500f, 500f)
                        radius = 400f

                        startAngle = -210f
                        sweepAngle = 120f

                        paint = Paint().apply {
                            style = Paint.Style.STROKE
                            strokeWidth = 30f
                            isAntiAlias = true
                            shader = progressSG
                            strokeCap = Paint.Cap.ROUND
                        }
                    }
                }
        )
    }
}
