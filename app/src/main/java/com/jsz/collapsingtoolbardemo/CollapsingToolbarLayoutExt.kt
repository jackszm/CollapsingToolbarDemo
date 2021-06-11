package com.jsz.collapsingtoolbardemo

import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
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
        if (scrollView.canScroll()) {
            enableScroll()
        } else {
            expand()
        }
    }
}

fun CollapsingToolbarLayout.expand() {
    (parent as? AppBarLayout)?.let {
        it.addOnOffsetChangedListener(onExpandedListener { disableScroll() })
        it.setExpanded(true, true)
    }
}

fun onExpandedListener(onExpandedBlock: (() -> Unit)) = object : OnOffsetChangedListener {
    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (verticalOffset == 0) {
            onExpandedBlock()
            appBarLayout.removeOnOffsetChangedListener(this)
        }
    }
}

//abstract class AppBarStateChangeListener : OnOffsetChangedListener {
//
//    enum class State {
//        EXPANDED, COLLAPSED, IDLE
//    }
//
//    private var mCurrentState = State.IDLE
//    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
//        mCurrentState = when {
//            i == 0 -> {
//                if (mCurrentState != State.EXPANDED) onStateChanged(appBarLayout, State.EXPANDED)
//                State.EXPANDED
//            }
//            abs(i) >= appBarLayout.totalScrollRange -> {
//                if (mCurrentState != State.COLLAPSED) onStateChanged(appBarLayout, State.COLLAPSED)
//                State.COLLAPSED
//            }
//            else -> {
//                if (mCurrentState != State.IDLE) onStateChanged(appBarLayout, State.IDLE)
//                State.IDLE
//            }
//        }
//    }
//
//    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State)
//}
