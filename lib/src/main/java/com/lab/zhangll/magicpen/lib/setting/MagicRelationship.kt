package com.lab.zhangll.magicpen.lib.setting

import android.graphics.PointF

/**
 * Created by zhangll on 2017/5/22.
 */
interface MagicRelationship {
    var start: PointF?
    var end: PointF?

    var width: Float?
    var height: Float?

    var leftMargin: Float
    var rightMargin: Float
    var topMargin: Float
    var bottomMargin: Float
}

private fun MagicRelationship.guardParameters(another: MagicSetting) {
    if (another.start == null || another.end == null) {
        throw Exception("不允许参照对象为空")
    }

    if (width == null || height == null) {
        throw Exception("宽高不能为空")
    }
}

fun MagicRelationship.reEnd() = PointF(start!!.x + width!!, start!!.y + height!!)

fun MagicRelationship.belowOf(another: MagicSetting) {
    guardParameters(another)

    start = PointF(start?.x ?: another.start!!.x,
            another.end!!.y)
    end = reEnd()
}

fun MagicRelationship.aboveOf(another: MagicSetting) {
    guardParameters(another)

    start = PointF(start?.x ?: another.start!!.x,
            another.start!!.y - height!!)
    end = reEnd()
}

fun MagicRelationship.rightOf(another: MagicSetting) {
    guardParameters(another)

    start = PointF(another.end!!.x,
            start?.y ?: another.start!!.y)
    end = reEnd()
}

fun MagicRelationship.leftOf(another: MagicSetting) {
    guardParameters(another)

    start = PointF(another.start!!.x - width!!,
            start?.y ?: another.start!!.y)
    end = reEnd()
}

fun MagicRelationship.alignTop(another: MagicSetting) {
    guardParameters(another)

    start = PointF(start?.x ?: another.start!!.x,
            another.start!!.y)
    end = reEnd()
}

fun MagicRelationship.alignBottom(another: MagicSetting) {
    guardParameters(another)

    start = PointF(start?.x ?: another.start!!.x,
            another.end!!.y - height!!)
    end = reEnd()
}

fun MagicRelationship.alignLeft(another: MagicSetting) {
    guardParameters(another)

    start = PointF(another.start!!.x,
            start?.y ?: another.start!!.y)
    end = reEnd()
}

fun MagicRelationship.alignRight(another: MagicSetting) {
    guardParameters(another)

    start = PointF(another.end!!.x - width!!,
            start?.y ?: another.start!!.y)
    end = reEnd()
}

//fun MagicRelationship.leftMargin(margin: Float) {
//    start = PointF((start?.x ?: 0f) + margin,
//            start?.y ?: 0f)
//    end = reEnd()
//}
//
//fun MagicRelationship.rightMargin(margin: Float) {
//    start = PointF((start?.x ?: 0f) - margin,
//            start?.y ?: 0f)
//    end = reEnd()
//}
//
//fun MagicRelationship.topMargin(margin: Float) {
//    start = PointF(start?.x ?: 0f,
//            (start?.y ?: 0f) + margin)
//    end = reEnd()
//}
//
//fun MagicRelationship.bottomMargin(margin: Float) {
//    start = PointF(start?.x ?: 0f,
//            (start?.y ?: 0f) - margin)
//    end = reEnd()
//}