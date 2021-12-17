package com.iwdael.multimap

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */
interface LayoutManager {
    fun layout(mode: Mode,patch: Patch,  maxPatchCount: Int, patchCount: Int,gab: Int, index: Int,  left: Int, top: Int, right: Int, bottom: Int)
}