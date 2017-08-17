package com.lab.zhangll.magicpen.lib.shapes.bitmap

import android.graphics.*
import com.lab.zhangll.magicpen.lib.MagicPen
import com.lab.zhangll.magicpen.lib.shapes.MagicShape

/**
 * Created by zhangll on 2017/6/6.
 */
class MagicBitmap : MagicShape() {
    var bitmap: Bitmap? = null
    override var paint: Paint = Paint()

    var center = PointF(0f, 0f)
        set(value) {
            field = value

            start = PointF(value.x - width / 2, value.y - height / 2)
            end = PointF(value.x + width / 2, value.y + height / 2)
        }

    var file: String? = null
        set(value) {
            if (value != null && field != value) {
                field = value
                bitmap = getBitmapFromFile(value)
            }
        }

    var src: Int? = null
        set(value) {
            if (value != null && field != value) {
                field = value
                bitmap = getBitmapFromSrc(value)
            }
        }

    override fun drawOn(canvas: Canvas?) = canvas?.drawBitmap(bitmap, left, top, paint)
    override fun containPoint(x: Float, y: Float) = containInRect(x, y)

    private fun getBitmapFromFile(path: String): Bitmap {
        val temp = BitmapFactory.decodeFile(path)

        // 若无指定宽高，则默认为图片宽高
        if (width == 0f || height == 0f) {
            width = temp.width.toFloat()
            height = temp.height.toFloat()

            return temp
        }

        return scale(temp, width / temp.width, height / temp.height)
    }

    private fun getBitmapFromSrc(id: Int): Bitmap {
        val temp = BitmapFactory.decodeResource(MagicPen.application.resources, id)

        // 若无指定宽高，则默认为图片宽高
        if (width == 0f || height == 0f) {
            width = temp.width.toFloat()
            height = temp.height.toFloat()

            return temp
        }

        return scale(temp, width / temp.width, height / temp.height)
    }

    private fun scale(temp: Bitmap, scaleX: Float, scaleY: Float): Bitmap {
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleX, scaleY)

        return Bitmap.createBitmap(temp, 0, 0, temp.width, temp.height, matrix, true)
    }
}