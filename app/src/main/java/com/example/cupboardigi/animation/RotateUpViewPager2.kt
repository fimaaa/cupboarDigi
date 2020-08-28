package com.example.cupboardigi.animation

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class RotateUpViewPager2: ViewPager2.PageTransformer {
    companion object {
        private const val ROT_MOD = -15f
    }

    override fun transformPage(page: View, position: Float) {
        val width = page.width.toFloat()
        val rotation = ROT_MOD * position

        page.pivotX = width * 0.5f
        page.pivotY = 0f
        page.translationX = 0f
        page.rotation = rotation
    }
}