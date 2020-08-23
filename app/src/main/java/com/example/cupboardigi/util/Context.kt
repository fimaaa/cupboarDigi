package com.example.cupboardigi.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.example.cupboardigi.R
import kotlinx.android.synthetic.main.layout_customtoast.view.*
import org.jetbrains.anko.layoutInflater


const val Toast_Error = -1
const val Toast_Default = 0

fun Context.showToast(
    message: CharSequence,
    typeToast: Int? = Toast_Default,
    isLong: Boolean? = true
) {
    val view = this.layoutInflater.inflate(R.layout.layout_customtoast, null)
    view.txtMessage.text = message
    when (typeToast) {
        Toast_Error -> {
            view.txtMessage.background = this.getDrawable(R.drawable.bg_toast_error)
        }
        else -> {
            view.txtMessage.background = this.getDrawable(R.drawable.bg_toast)
        }
    }

    val toast = Toast(this)
    toast.duration = if (isLong != false) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
    toast.view = view
    toast.show()
}