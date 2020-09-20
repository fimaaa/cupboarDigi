package com.example.cupboardigi.util

import android.content.Context
import android.util.DisplayMetrics
import androidx.appcompat.app.AlertDialog
import com.example.cupboardigi.R
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.windowManager

object Loading {
    private lateinit var builder: AlertDialog.Builder
    private lateinit var loading: AlertDialog

    fun showLoadingGif(context: Context) {
        val view = context.layoutInflater.inflate(R.layout.item_loading_gif, null)
        builder = AlertDialog.Builder(context)
        builder.setView(view)
        builder.setCancelable(false)
        loading = builder.create()
        loading.show()

        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val displayWidth = displayMetrics.widthPixels
        val dialogWindowWidth = (displayWidth * 0.23f).toInt()
        loading.window?.setBackgroundDrawableResource(R.drawable.bg_rounded)
        loading.window?.setLayout(dialogWindowWidth, dialogWindowWidth)
    }

    fun hideLoading() {
        if(::loading.isInitialized){
            loading.dismiss()
        }
    }
}