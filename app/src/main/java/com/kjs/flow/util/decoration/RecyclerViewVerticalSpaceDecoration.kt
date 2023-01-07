package com.kjs.flow.util.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kjs.flow.util.extention.getIntDp

class RecyclerViewVerticalSpaceDecoration(private val spaceHeight: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = spaceHeight.getIntDp
    }
}