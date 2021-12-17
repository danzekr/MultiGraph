package com.iwdael.multimap

import android.widget.ImageView

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */
interface MapLoader {
    fun load(view: ImageView, data: Any)
}