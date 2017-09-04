package com.lab.zhangll.magicpen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.lab.zhangll.magicpen.adapter.EntryListAdapter
import com.lab.zhangll.magicpen.extension.find
import com.lab.zhangll.magicpen.extension.start

/**
 * Created by xiasuhuei321 on 2017/7/31.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */
class EntryActivity : AppCompatActivity() {
    var lv_list: ListView? = null
    var adapter: EntryListAdapter? = null
    var dataList: Array<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        lv_list = find<ListView>(R.id.listLv)
        adapter = EntryListAdapter()
        adapter!!.init(this)
        lv_list?.adapter = adapter
        dataList = resources.getStringArray(R.array.entry)
        adapter!!.setData(dataList!!)

        lv_list!!.setOnItemClickListener {
            _, _, pos, _ ->
            when (pos) {
                0 -> start<GravityProgressActivity>()
                1 -> start<SlideBarActivity>()
                2 -> start<ArcActivity>()
                3 -> start<MainActivity>()
                4 -> start<ShapeSampleActivity>()
                5 -> start<ChartActivity>()
            }
        }
    }

}