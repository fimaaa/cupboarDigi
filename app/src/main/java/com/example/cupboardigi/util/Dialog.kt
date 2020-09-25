package com.example.cupboardigi.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.cupboardigi.R
import com.example.cupboardigi.data.model.item.ItemScreen
import com.example.cupboardigi.data.model.item.ItemSeries
import com.example.cupboardigi.data.model.item.ItemType
import com.example.cupboardigi.databinding.DialogAddScreenBinding
import com.example.cupboardigi.databinding.DialogAddSeriesBinding
import com.example.cupboardigi.ui.adapter.AdapterItemType
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
        val binding: DialogAddScreenBinding =
            DataBindingUtil.inflate(context.layoutInflater, R.layout.dialog_add_screen, null, false)
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

    @SuppressLint("SetTextI18n")
    fun dialogAddSeries(
        context: Context,
        listItemType: List<ItemType>?,
        listenerLink: (ItemSeries) -> Unit,
        listenerAddImage: () ->  Unit
    ) {
        val binding: DialogAddSeriesBinding =
            DataBindingUtil.inflate(context.layoutInflater, R.layout.dialog_add_series, null, false)
        if(listItemType?.isNotEmpty() == true){
            val adapter = AdapterItemType(listItemType)
            binding.spinnerItemtypeAddseries.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (adapter.getItem(position).formItem){
                        ItemType.FormType.BOOK_SMALL.form,  ItemType.FormType.BOOK_MEDIUM.form,  ItemType.FormType.BOOK_LARGE.form -> {
                            binding.cbIsalreadyend.isChecked = false
                            binding.cbIsalreadyend.isClickable = true
                        }
                        else -> {
                            binding.cbIsalreadyend.isChecked = true
                            binding.cbIsalreadyend.isClickable = false
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
            binding.spinnerItemtypeAddseries.adapter = adapter
        }else{
            binding.spinnerItemtypeAddseries.visibility = View.GONE
        }
        binding.btnAddvolumeAddseries.onClick {
            val volume: Int = try {
                binding.etVolumeAddseries.text.toString().toInt()
            } catch (e: Exception) {
                0
            }
            binding.etVolumeAddseries.setText("${volume + 1}")

        }
        binding.btnRemovevolumeAddseries.onClick {
            val volume: Int = try {
                binding.etVolumeAddseries.text.toString().toInt()
            } catch (e: Exception) {
                0
            }
            binding.etVolumeAddseries.setText(
                if (volume > 1) {
                    "${volume - 1}"
                } else {
                    "0"
                }

            )
        }
        binding.ivCoverSeries.onClick {
            listenerAddImage.invoke()
        }
        binding.btnAddAddingseries.onClick {
            dialog.dismiss()
            listenerLink.invoke(
                ItemSeries(
                    0L,
                    binding.textFieldNameAddseries.editText?.text.toString(),
                    (binding.spinnerItemtypeAddseries.selectedItem as ItemType),
                    binding.etVolumeAddseries.text.toString().toInt(),
                    binding.cbIsalreadyend.isChecked,
                    ""
                )
            )
        }
        builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_rounded)
        binding.btnCancelAddingseries.onClick {
            dialog.dismiss()
        }
        dialog.show()

    }

}