package com.example.cupboardigi.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.cupboardigi.R
import com.example.cupboardigi.data.model.item.ItemScreen
import com.example.cupboardigi.databinding.DialogAddScreenBinding
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.sdk27.coroutines.onClick

object Dialog {
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    fun dialogAddScreen(
        context: Context,
        idBoard: Long,
        listenerLink: (ItemScreen) -> Unit
    ) {
        val binding: DialogAddScreenBinding = DataBindingUtil.inflate( context.layoutInflater, R.layout.dialog_add_screen, null, false)
        binding.btnAddAddingscreen.onClick {
            dialog.dismiss()
            listenerLink.invoke(
                ItemScreen(
                    0,
                    idBoard,
                    binding.etNameScreen.text.toString(),
                    binding.cbIscoveropen.isChecked
                )
            )
        }
        builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_rounded)
        binding.btnCancelAddingscreen.onClick {

        }
        dialog.show()

    }

}