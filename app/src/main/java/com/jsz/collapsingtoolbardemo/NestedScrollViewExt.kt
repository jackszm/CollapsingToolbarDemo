package com.jsz.collapsingtoolbardemo

import androidx.core.widget.NestedScrollView

fun NestedScrollView.canScroll(): Boolean {
    return getChildAt(0)?.let {
        height < it.height + paddingTop + paddingBottom
    } ?: false
}



//view.addOnLayoutChangeListener( new View.OnLayoutChangeListener()
//{
//    public void onLayoutChange( View v,
//      int left,    int top,    int right,    int bottom,
//      int leftWas, int topWas, int rightWas, int bottomWas )
//    {
//        int widthWas = rightWas - leftWas; // Right exclusive, left inclusive
//        if( v.getWidth() != widthWas )
//        {
//            // Width has changed
//        }
//        int heightWas = bottomWas - topWas; // Bottom exclusive, top inclusive
//        if( v.getHeight() != heightWas )
//        {
//            // Height has changed
//        }
//    }
//});
