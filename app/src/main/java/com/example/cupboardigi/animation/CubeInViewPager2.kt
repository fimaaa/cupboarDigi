package com.example.cupboardigi.animation

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class CubeInViewPager2: ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.pivotX = if (position > 0) 0f else page.width.toFloat()
        page.pivotY = 0f
        page.rotationY = -90f * position
    }
}