package com.lab.zhangll.magicpen

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lab.zhangll.magicpen.lib.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                magicPen {
                    val line = line {
                        start = PointF(0f, 1000f) // 线条起点
                        end = PointF(1000f, 1000f) // 线条终点
                    }

                    val bigOne = circle {
                        radius = 200f // 圆半径

                        aboveOf(line) // 在线条上面
                        leftMargin = 500f // 左边距
                        bottomMargin = 10f // 下边距

                        paint = Paint().apply { color = Color.RED } // 红色
                    }

                    text {
                        text = "我是一只小小鸟我是一只小小鸟"
                        centerIn(bigOne)
                    }

                    circle {
                        radius = 50f // 半径
                        centerIn(bigOne) // 在大圆中间

                        gesture {
                            onClick = { Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show() } // 点击时弹框
                            onDragBy = { x, y -> moveBy(x, y) } // 跟着拖动的手指动
                            onRelease = { smoothMoveToOrigin() } // 放手后滑动到原点
                        }
                    }
                }
        )
    }
}
