package com.example.cupboardigi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cupboardigi.databinding.ItemScreenBoardBinding

class AdapterScreen(private val listener: (Int?) -> Unit) :
    RecyclerView.Adapter<AdapterScreen.ViewHolder>() {

    class ViewHolder(private var binding: ItemScreenBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(
            listener: (Int?) -> Unit
        ) {
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            ItemScreenBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listener)
    }
}