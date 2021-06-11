package com.jsz.collapsingtoolbardemo

import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout.LayoutParams
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*
import com.google.android.material.appbar.CollapsingToolbarLayout

fun CollapsingToolbarLayout.enableScroll() {
    val layoutParams = layoutParams as LayoutParams
    layoutParams.scrollFlags = SCROLL_FLAG_SCROLL or
            SCROLL_FLAG_EXIT_UNTIL_COLLAPSED or
            SCROLL_FLAG_SNAP
    this.layoutParams = layoutParams
}

fun CollapsingToolbarLayout.disableScroll() {
    val layoutParams = layoutParams as LayoutParams
    layoutParams.scrollFlags = SCROLL_FLAG_NO_SCROLL
    this.layoutParams = layoutParams
}

fun CollapsingToolbarLayout.adoptScrollingFlagsBasedOn(scrollView: NestedScrollView) {
    scrollView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
        if(scrollView.canScroll()) enableScroll() else disableScroll()
    }
}
