package com.iwdael.multigraph

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * desc   : multiGraph
 * version: 1.0
 */
open class MultiGraph(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    companion object {
        var defaultMultiGraphLoader: MultiGraphLoader = DefaultMultiGraphLoader()
        var defaultLayoutManager: LayoutManager = DefaultLayoutManager()
    }

    private val maxPatchCount: Int
    private val gapSize: Int
    private val defaultResource: Int
    private var patchesLeft: Int = 0
    private var patchesTop: Int = 0
    private var patchesRight: Int = 0
    private var patchesBottom: Int = 0
    private var cachePatches = mutableMapOf<Int, Patch>()
    private var dataSet = mutableListOf<Any>()
    private var mode = Mode.AVATAR
    private var onMapClickListener: ((ImageView, Any, Int) -> Unit)? = null

    fun setOnMapClickListener(l: ((view: ImageView, data: Any, index: Int) -> Unit)) {
        this.onMapClickListener = l
    }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MultiGraph)
        maxPatchCount = ta.getInteger(R.styleable.MultiGraph_maxPatchCount, 9)
        gapSize = ta.getDimensionPixelSize(R.styleable.MultiGraph_gapSize, 0)
        mode = if (ta.getInt(R.styleable.MultiGraph_mode, 1) == 1) Mode.AVATAR else Mode.GRID
        defaultResource = ta.getDimensionPixelSize(R.styleable.MultiGraph_defaultResource, -1)
        ta.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        patchesLeft = paddingLeft
        patchesTop = paddingTop
        patchesRight = measureWidth - paddingRight
        patchesBottom = measureHeight - paddingBottom
        dispatchPatch(childCount)
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) = layoutChildrenView()

    fun setData(data: Array<Any>) {
        this.dataSet.clear()
        this.dataSet.addAll(data)
        dispatchPatch(Math.min(this.dataSet.size, maxPatchCount))
        layoutChildrenView()
    }

    private fun layoutChildrenView() {
        val childrenCount = childCount
        for (index in 0 until childrenCount) {
            val path = createPatch(index)
            path.layout()
            defaultMultiGraphLoader.load(path.view, dataSet[index])
        }
    }

    private fun dispatchPatch(newCount: Int) {
        val oldCount = childCount
        if (oldCount < newCount) {
            for (index in oldCount until newCount) addView(createPatch(index).view, generateDefaultLayoutParams())
        } else {
            removeViews(newCount, oldCount - newCount)
        }
        for (index in 0 until newCount) {
            defaultLayoutManager.layout(mode, createPatch(index), maxPatchCount, newCount, gapSize, index, patchesLeft, patchesTop, patchesRight, patchesBottom)
        }
    }

    private fun createPatch(index: Int): Patch {
        synchronized(cachePatches) {
            return cachePatches.getOrPut(index) {
                Patch(Rect(), ImageView(context).apply { scaleType = ImageView.ScaleType.CENTER_CROP }) {
                    val i = indexOfChild(it)
                    onMapClickListener?.invoke(it, dataSet[i], i)
                }
            }
        }
    }
}