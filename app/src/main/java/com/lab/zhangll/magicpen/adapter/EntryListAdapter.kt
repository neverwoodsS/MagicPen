package com.lab.zhangll.magicpen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by xiasuhuei321 on 2017/7/31.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */
class EntryListAdapter : BaseAdapter() {
    var dataList: Array<String>? = null
    var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    fun setData(dataList: Array<String>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    // TODO 用magicpen来实现这个view
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        if (convertView == null) {
            view = LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_1, null)
        }

        val text1 = view?.findViewById(android.R.id.text1) as TextView
        text1.text = dataList?.get(position)
        return convertView ?: view
    }

    override fun getItem(position: Int): Any {
        return dataList?.get(position) ?: ""
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataList?.size ?: 0
    }
}