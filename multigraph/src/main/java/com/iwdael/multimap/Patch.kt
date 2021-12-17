package com.iwdael.multimap

import android.graphics.Rect
import android.widget.ImageView

class Patch(val rect: Rect, val view: ImageView, call: (ImageView) -> Unit = {}) {
    init {
        view.setOnClickListener { call.invoke(view) }
    }

    fun layout() {
        view.layout(rect.left, rect.top, rect.right, rect.bottom)
    }
}