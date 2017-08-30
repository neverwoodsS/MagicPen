package com.lab.zhangll.magicpen

import android.graphics.*
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import com.lab.zhangll.magicpen.lib.*
import com.lab.zhangll.magicpen.lib.base.centerX
import com.lab.zhangll.magicpen.lib.paint.paint
import com.lab.zhangll.magicpen.lib.shapes.MagicShape
import com.lab.zhangll.magicpen.lib.shapes.MagicText

/**
 * Created by zhangll on 2017/7/21.
 */
class GravityProgressActivity : AppCompatActivity() {

    val tagWidth = 100f
    val tagHeight = 60f
    var text: MagicText? = null
    val formula = computeFormula(800f, 30f)

    var dragPoint = PointF(100f, 200f)

    var shapes = mutableListOf<MagicShape>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
                magicPen {
                    line {
                        start = PointF(100f, 200f)
                        end = dragPoint
                        paint = paint { strokeWidth = 5f }
                    }.wait()

                    line {
                        start = dragPoint
                        end = PointF(900f, 200f)
                        paint = paint { strokeWidth = 5f }
                    }.wait()

                    val point = circle {
                        center = dragPoint
                        radius = 0f
                    }.wait()

                    val tag = tag {
                        width = tagWidth
                        height = tagHeight
                        centerIn(point)
                        aboveOf(point)
                        bottomMargin = 25f
                    }.wait()

                    text = text {
                        content = "90%"
                        paint = Paint().apply { textSize = 40f }
                        centerIn(tag)
                    }.wait()
                }
        )

        object: CountDownTimer(30000, 16) {
            override fun onFinish() = Unit
            override fun onTick(millisUntilFinished: Long) = progressing()
        }.start()
    }

    private fun computeFormula(end: Float, maxHeight: Float): (Float) -> Float {
        val a = -(maxHeight / end / end * 4)
        val b = 4 * maxHeight / end
        return { x: Float -> a * x * x + b * x }
    }

    private fun progressing() {
        if (dragPoint.x < 900) {
            dragPoint.x += 2
            dragPoint.y = formula.invoke(dragPoint.x - 100) + 200
            text?.content = "${((dragPoint.x - 100) / 800f * 100).toInt()}%"

            println("dragPoint: ${dragPoint.x}, ${dragPoint.y}")
            shapes.forEach {
                it.reBounds()
                it.invalidate()
            }
        }
    }

    class Tag : MagicShape() {
        override var paint = Paint().apply { style = Paint.Style.STROKE }

        override fun drawOn(canvas: Canvas?) {
            canvas?.drawPath(Path().apply {
                moveTo(left, top)
                lineTo(right, top)
                lineTo(right, bottom)
                lineTo(right - width / 3, bottom)
                lineTo(centerX, bottom + width / 6)
                lineTo(left + width / 3, bottom)
                lineTo(left, bottom)
                lineTo(left, top)
            }, paint)
        }

        override fun containPoint(x: Float, y: Float) = containInRect(x, y)
    }

    fun MagicView.tag(set: Tag.() -> Unit) = settingOf(set)

    fun <T : MagicShape> T.wait(): T {
        shapes.add(this)
        return this
    }
}