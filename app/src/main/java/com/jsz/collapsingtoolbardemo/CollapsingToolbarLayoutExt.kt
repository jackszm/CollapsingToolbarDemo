package com.jsz.collapsingtoolbardemo

import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout

fun CollapsingToolbarLayout.adoptScrollingFlagsBasedOn(scrollView: NestedScrollView) {
    scrollView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
        if (scrollView.canScroll()) {
            enableScroll()
        } else {
            expandAndDisableScroll()
        }
    }
}

private fun CollapsingToolbarLayout.enableScroll() {
    val layoutParams = layoutParams as LayoutParams
    layoutParams.scrollFlags = SCROLL_FLAG_SCROLL or
            SCROLL_FLAG_EXIT_UNTIL_COLLAPSED or
            SCROLL_FLAG_SNAP
    this.layoutParams = layoutParams
}

private fun CollapsingToolbarLayout.expandAndDisableScroll() {
    (parent as? AppBarLayout)?.let {
        it.addOnOffsetChangedListener(onExpandedListener { disableScroll() })
        it.setExpanded(true, true)
    }
}

private fun CollapsingToolbarLayout.disableScroll() {
    val layoutParams = layoutParams as LayoutParams
    layoutParams.scrollFlags = SCROLL_FLAG_NO_SCROLL
    this.layoutParams = layoutParams
}

private fun onExpandedListener(onExpandedBlock: (() -> Unit)) = object : OnOffsetChangedListener {
    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (verticalOffset == 0) {
            onExpandedBlock()
            appBarLayout.removeOnOffsetChangedListener(this)
        }
    }
}
