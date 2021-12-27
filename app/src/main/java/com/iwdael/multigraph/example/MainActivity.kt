package com.iwdael.multigraph.example

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.iwdael.multigraph.MultiGraph
import com.iwdael.multigraph.MultiGraphLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MultiGraph.defaultMultiGraphLoader = object :MultiGraphLoader{
            override fun load(view: ImageView, data: Any) {
                view.setImageResource(data as Int)
            }
        }
        setContentView(R.layout.activity_main)
        map.setData(arrayOf(
                R.drawable.map,
                R.drawable.map
        ))
        map.setOnGraphClickListener { view, data, index ->
            Log.v("dzq", "view:${view}, data:${data}, index:${index}")
        }
    }

    var count = 0
    fun onMapClick(view: View) {
        count++
        val size = count % 9
        val list = mutableListOf<Int>()
        for (index in 0..size)
            list.add(R.drawable.map)
        map.setData(list.toTypedArray())
    }

    fun onClearClick(view: View) {
        map.setData(arrayOf())
    }
}