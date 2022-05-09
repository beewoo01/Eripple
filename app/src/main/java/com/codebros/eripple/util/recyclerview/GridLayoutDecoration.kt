package com.codebros.eripple.util.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridLayoutDecoration(
    private val size1: Int, private val size2: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildLayoutPosition(view)
        val itemCount = state.itemCount

        if (position == 0 || position == 1) {
            outRect.top = size1
            outRect.bottom = size2
        } else {
            outRect.bottom = size2
        }

        val lp = view.layoutParams as GridLayoutManager.LayoutParams

        val spanIndex = lp.spanIndex

        if (spanIndex == 0) {
            outRect.left = size1
            outRect.right = size2

        } else if (spanIndex == 1) {
            outRect.left = size1
            outRect.right = size2
        }

    }

}