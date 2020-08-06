package com.alesh.baseproject.util.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridLayoutDecorator(
    private val columns: Int,
    private val marginInPx: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            val position: Int = parent.getChildAdapterPosition(view)
            val column: Int = position % columns

            left = column * marginInPx / columns
            right = marginInPx - (column + 1) * marginInPx / columns
            if (position >= columns) top = marginInPx
        }
    }
}