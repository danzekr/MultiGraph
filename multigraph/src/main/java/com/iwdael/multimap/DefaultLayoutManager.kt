package com.iwdael.multimap

class DefaultLayoutManager : LayoutManager {
    override fun layout(mode: Mode, patch: Patch, maxPatchCount: Int, patchCount: Int, gab: Int, index: Int, left: Int, top: Int, right: Int, bottom: Int) {
        if (mode == Mode.GRID)
            layoutModeGrid(patch, maxPatchCount, patchCount, gab, index, left, top, right, bottom)
        else
            layoutModeAvatar(patch, maxPatchCount, patchCount, gab, index, left, top, right, bottom)
    }

    private fun layoutModeAvatar(patch: Patch, maxPatchCount: Int, patchCount: Int, gab: Int, index: Int, left: Int, top: Int, right: Int, bottom: Int) {
        val width = right - left
        val height = bottom - top
        if (patchCount == 1) patch.rect.set(left, top, right, bottom)
        else if (patchCount == 2) {
            val patchWidth = (width - gab) / 2
            val patchHeight = (height - gab) / 2
            if (index == 0) {
                patch.rect.set(
                        left,
                        top + height / 2 - patchHeight / 2,
                        left + patchWidth,
                        top + height / 2 + patchHeight / 2
                )
            } else {
                patch.rect.set(
                        left + patchWidth + gab,
                        top + height / 2 - patchHeight / 2,
                        left + patchWidth + gab + patchWidth,
                        top + height / 2 + patchHeight / 2
                )
            }
        } else if (patchCount == 3) {
            val patchWidth = (width - gab) / 2
            val patchHeight = (height - gab) / 2
            if (index == 0) {
                patch.rect.set(
                        left + width / 2 - patchWidth / 2,
                        top,
                        left + width / 2 + patchWidth / 2,
                        top + patchHeight
                )
            } else if (index == 1) {
                patch.rect.set(
                        left,
                        bottom - patchHeight,
                        left + patchWidth,
                        bottom
                )
            } else if (index == 2) {
                patch.rect.set(
                        right - patchWidth,
                        bottom - patchHeight,
                        right,
                        bottom
                )
            }
        } else {
            layoutModeGrid(patch, maxPatchCount, patchCount, gab, index, left, top, right, bottom)
        }
    }

    private fun layoutModeGrid(patch: Patch, maxPatchCount: Int, patchCount: Int, gab: Int, index: Int, left: Int, top: Int, right: Int, bottom: Int) {
        val gabSize = if (patchCount == 1) 0 else if (patchCount <= 4) 1 else 2
        val grid = if (patchCount == 1) 1 else if (patchCount <= 4) 2 else 3
        val width = right - left
        val height = bottom - top
        val patchWidth = (width - gabSize * gab) / grid
        val patchHeight = (height - gabSize * gab) / grid
        for (row in 0 until grid) {
            for (col in 0 until grid) {
                if (row * grid + col == index) {
                    val l = col * (patchWidth + gab)
                    val t = row * (patchHeight + gab)
                    patch.rect.set(left + l, top + t, left + l + patchWidth, top + t + patchHeight)
                    break
                }
            }
        }
    }

}