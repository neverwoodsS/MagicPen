package com.lab.zhangll.magicpen

import android.graphics.Paint
import android.graphics.PointF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ListView
import com.lab.zhangll.magicpen.adapter.EntryListAdapter
import com.lab.zhangll.magicpen.extension.find
import com.lab.zhangll.magicpen.lib.*
import com.lab.zhangll.magicpen.lib.paint.paint

/**
 * Created by xiasuhuei321 on 2017/8/1.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */
class ShapeSampleActivity : AppCompatActivity() {
    // FIXME：绘制的bitmap和圆形，设置其父View gravity参数失效
    var lv_list: ListView? = null
    var ll_container: LinearLayout? = null
    var dataList: Array<String>? = null

    var rect: View? = null
    var arc: View? = null
    var bmp: View? = null
    var circle: View? = null
    var line: View? = null
    var text: View? = null

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
                    addView(createBmpView())
                }
                3 -> {
                    addView(createCircle())
                }
                4 -> {
                    addView(createText())
                }
                5 -> {
                    addView(createLine())
                }
            }
        }
    }

    fun createRect(): View {
        if (rect == null) {
            rect = magicPen {
                rect {
                    setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
                    start = PointF(0f, 0f)
                    end = PointF(100f, 100f)
                    centerInParent = true
                    gesture {
                        onDragBy = { x, y -> moveBy(x, y) }
                    }
                }

            }
            val params = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
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
                    centerInParent = true
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

    fun createBmpView(): View {
        if (bmp == null) {
            bmp = magicPen {
                bitmap {
                    centerInParent = true
                    start = PointF(0f, 0f)
                    end = PointF(300f, 300f)
                    src = R.mipmap.ic_launcher_round
                }
            }
        }

        return bmp!!
    }

    fun createCircle(): View {
        if (circle == null) {
            circle = magicPen {
                circle {
                    center = PointF(200f, 200f)
                    radius = (100f)
                    centerInParent = true
                    paint {
                        color = resources.getColor(R.color.abc_color_highlight_material)
                    }
                    gesture {
                        onDragBy = { x, y -> moveBy(x, y) }
                    }
                }
            }
        }
        return circle!!
    }

    fun createText(): View {
        if (text == null) {
            text = magicPen {
                text {
                    content = "hello world"
                    center = PointF(100f, 100f)
                    width = 100f
                    height = 100f
                    centerInParent = true
                    paint = Paint().apply { textSize = 40f }
                }
            }
        }
        return text!!
    }

    fun createLine(): View {
        if (line == null) {
            line = magicPen {
                line {
                    centerInParent = true
                    start = PointF(100f, 100f)
                    end = PointF(200f, 200f)
                    paint = Paint().apply { strokeWidth = 10f }
                }
            }
        }
//        line!!.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        return line!!
    }

    fun addView(v: View) {
        val childView = ll_container!!.getChildAt(1)
        if (childView != null) {
            ll_container!!.removeViewAt(1)
        }
        ll_container!!.addView(v)
    }
}