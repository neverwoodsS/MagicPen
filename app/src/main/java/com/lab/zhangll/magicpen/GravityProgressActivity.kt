package com.lab.zhangll.magicpen

import android.graphics.*
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import com.lab.zhangll.magicpen.lib.*
import com.lab.zhangll.magicpen.lib.paint.paint
import com.lab.zhangll.magicpen.lib.setting.MagicSetting
import com.lab.zhangll.magicpen.lib.shapes.MagicShape
import com.lab.zhangll.magicpen.lib.shapes.width

/**
 * Created by zhangll on 2017/7/21.
 */
class GravityProgressActivity : AppCompatActivity() {

    val tagWidth = 100f
    val tagHeight = 60f
    val formula = computeFormula(800f, 60f)
    var dragPoint = PointF(100f, 200f)

    var settings = mutableListOf<MagicSetting<out MagicShape>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
                magicPen {
                    line {
                        start = PointF(100f, 200f)
                        end = dragPoint
                        paint = paint { strokeWidth = 3f }
                    }.apply { settings.add(this) }

                    line {
                        start = dragPoint
                        end = PointF(900f, 200f)
                        paint = paint { strokeWidth = 3f }
                    }.apply { settings.add(this) }

                    val point = circle {
                        center = dragPoint
                        radius = 0f
                    }.apply { settings.add(this) }

                    val tag = tag {
                        width = tagWidth
                        height = tagHeight
                        centerIn(point)
                        aboveOf(point)
                        bottomMargin = 25f
                    }.apply { settings.add(this) }

                    text {
                        content = "90%"
                        paint = Paint().apply { textSize = 40f }
                        centerIn(tag)
                    }.apply { settings.add(this) }
                }
        )

        object: CountDownTimer(30000, 16) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                progressing()
            }
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

            println("dragPoint: ${dragPoint.x}, ${dragPoint.y}")
            settings.forEach { it.invalidate() }
        }
    }



    class Tag : MagicShape() {

        var center: PointF? = null

        override var paint = Paint().apply { style = Paint.Style.STROKE }

        override fun drawOn(canvas: Canvas?) {
            canvas?.drawPath(Path().apply {
                moveTo(left, top)
                lineTo(right, top)
                lineTo(right, bottom)
                lineTo(right - width / 3, bottom)
                lineTo(center!!.x, bottom + width / 6)
                lineTo(left + width / 3, bottom)
                lineTo(left, bottom)
                lineTo(left, top)
            }, paint)
        }

        override fun containPoint(x: Float, y: Float) = containInRect(x, y)
    }

    class TagSetting(tag: Tag) : MagicSetting<Tag>(tag) {

        var center: PointF? = null

        override fun product(shape: Tag): Tag {
            if (center != null && width != null && height != null) {
                start = PointF(center!!.x - width!! / 2, center!!.y - height!! / 2)
                end = PointF(center!!.x + width!! / 2, center!!.y + height!! / 2)
            }

            if (start != null && end != null) {
                shape.center = PointF(start!!.x / 2 + end!!.x / 2,  start!!.y / 2 + end!!.y / 2)
                shape.left = start!!.x
                shape.top = start!!.y
                shape.right = end!!.x
                shape.bottom = end!!.y
            } else {
                throw Exception("条件不充足")
            }


            return shape
        }
    }

    fun MagicView.tag(set: TagSetting.() -> Unit) = settingOf(set)
}