package com.lab.zhangll.magicpen.lib.shapes.bitmap

import com.lab.zhangll.magicpen.lib.MagicPen
import com.lab.zhangll.magicpen.lib.setting.MagicSetting
import android.graphics.*


/**
 * Created by zhangll on 2017/6/6.
 */
class MagicBitmapSetting(val shape: MagicBitmap) : MagicSetting<MagicBitmap>(shape) {
    var center: PointF? = null

    var paint: Paint? = null

    var file: String? = null
        set(value) {
            if (value != null && field != value) {
                field = value
                shape.bitmap = getBitmapFromFile(value)
            }
        }

    var src: Int? = null
        set(value) {
            if (value != null && field != value) {
                field = value
                shape.bitmap = getBitmapFromSrc(value)
            }
        }

    override fun product(shape: MagicBitmap): MagicBitmap {
        // 根据不同配置获取 bitmap
        if (src != null) {
            shape.bitmap = getBitmapFromSrc(src!!)
        } else if (file != null) {
            shape.bitmap = getBitmapFromFile(file!!)
        } else {
            throw Exception("bitmap 来源为空")
        }

        if (center != null && width != null && height != null) {
            start = PointF(center!!.x - width!! / 2, center!!.y - height!! / 2)
            end = PointF(center!!.x + width!! / 2, center!!.y + height!! / 2)
        }

        if (start != null && end != null) {
            shape.left = start!!.x
            shape.top = start!!.y
            shape.right = end!!.x
            shape.bottom = end!!.y
            shape.paint = paint ?: Paint()
        } else {
            throw Exception("条件不充足")
        }

        return shape
    }

    private fun getBitmapFromFile(path: String): Bitmap {
        val temp = BitmapFactory.decodeFile(path)

        // 若无指定宽高，则默认为图片宽高
        if (width == null || height == null) {
            width = temp.width.toFloat()
            height = temp.height.toFloat()

            return temp
        }

        return scale(temp, width!! / temp.width, height!! / temp.height)
    }

    private fun getBitmapFromSrc(id: Int): Bitmap {
        val temp = BitmapFactory.decodeResource(MagicPen.application.resources, id)

        // 若无指定宽高，则默认为图片宽高
        if (width == null || height == null) {
            width = temp.width.toFloat()
            height = temp.height.toFloat()

            return temp
        }

        return scale(temp, width!! / temp.width, height!! / temp.height)
    }

    private fun scale(temp: Bitmap, scaleX: Float, scaleY: Float): Bitmap {
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleX, scaleY)

        return Bitmap.createBitmap(temp, 0, 0, temp.width, temp.height, matrix, true)
    }
}