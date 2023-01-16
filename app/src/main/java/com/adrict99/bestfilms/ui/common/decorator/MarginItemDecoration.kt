package com.adrict99.bestfilms.ui.common.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val space: Int,
    private val verticalMode: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (verticalMode) {
                if (parent.getChildAdapterPosition(view) == 0) top = space
                left = space
            } else {
                if (parent.getChildAdapterPosition(view) == 0) left = space
                top = space
            }
            right = space
            bottom = space
        }
    }

}