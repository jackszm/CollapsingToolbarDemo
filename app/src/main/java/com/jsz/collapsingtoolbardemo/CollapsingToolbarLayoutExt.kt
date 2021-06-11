package com.jsz.collapsingtoolbardemo

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