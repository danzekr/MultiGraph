package com.danzekr.multimap

import android.util.Log
import android.widget.ImageView

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */
class DefaultMapLoader : MapLoader {
    override fun load(view: ImageView, data: Any) {
//        Log.v("dzq", "load:${view} into :${data}")
        view.setImageResource(data as Int)
    }
}