package com.lab.zhangll.magicpen

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import com.lab.zhangll.magicpen.adapter.EntryListAdapter
import com.lab.zhangll.magicpen.extension.find
import com.lab.zhangll.magicpen.lib.arc
import com.lab.zhangll.magicpen.lib.magicPen
import com.lab.zhangll.magicpen.lib.paint.paint
import com.lab.zhangll.magicpen.lib.rect

/**
 * Created by xiasuhuei321 on 2017/8/1.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */
class ShapeSampleActivity : AppCompatActivity() {
    var lv_list: ListView? = null
    var ll_container: LinearLayout? = null
    var dataList: Array<String>? = null

    var rect: View? = null
    var arc: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_shape)
        lv_list = find<ListView>(R.id.lv_list)
        ll_container = find<LinearLayout>(R.id.ll_container)

        var adapter: EntryListAdapter = EntryListAdapter()
        adapter.init(this)
        dataList = resources.getStringArray(R.array.shape)
        lv_list!!.adapter = adapter
        adapter.setData(dataList!!)

        lv_list!!.setOnItemClickListener {
            _, _, pos, _ ->
            when (pos) {
                0 -> {
                    addView(createRect())
                }
                1 -> {
                    addView(createArc())
                }
                2 -> {

                }
                3 -> {
                    // TODO 圆形
                }
                4 -> {
                    // TODO 文字
                }
                5 -> {
                    // TODO 线
                }
            }
        }
    }

    fun createRect(): View {
        if (rect == null) {
            rect = magicPen {
                rect {
                    width = 100f
                    height = 100f
                    setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
                    start = PointF(0f, 0f)
                    end = PointF(100f, 100f)
                }

            }
            val params = ViewGroup.LayoutParams(100, 100)
            rect!!.layoutParams = params
        }

        return rect!!
    }

    fun createArc(): View {
        if (arc == null) {
            arc = magicPen {
                arc {
                    start = PointF(0f, 0f)
                    end = PointF(0f, 0f)
                    center = PointF(500f, 500f)
                    radius = 400f
                    startAngle = -210f
                    sweepAngle = 240f

                    paint = paint {
                        strokeWidth = 30f
                        style = Paint.Style.STROKE
                        strokeCap = Paint.Cap.ROUND
                    }
                }
            }
        }

        return arc!!
    }

    fun addView(v: View) {
        val childView = ll_container!!.getChildAt(1)
        if (childView != null) {
            ll_container!!.removeViewAt(1)
        }
        ll_container!!.addView(v)
        ll_container!!.gravity = Gravity.CENTER_HORIZONTAL
    }
}