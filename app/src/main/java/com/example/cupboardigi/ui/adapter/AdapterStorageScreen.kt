package com.example.cupboardigi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cupboardigi.data.model.item.ItemScreen
import com.example.cupboardigi.data.model.item.ItemStorageUser
import com.example.cupboardigi.databinding.ItemStorageScreenBinding

class AdapterStorageScreen(private val listener: (ItemStorageUser?) -> Unit) :
    RecyclerView.Adapter<AdapterStorageScreen.ViewHolder>() {
    private var listStorageUser: List<ItemStorageUser?>? = listOf()
    fun setListReward(listStorageUser: List<ItemStorageUser?>?) {
        this.listStorageUser = listStorageUser
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: ItemStorageScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(
            data: ItemStorageUser?,
            listener: (ItemStorageUser?) -> Unit
        ) {
            binding.tvTitleStorage.text = data?.idStorage?.typeItem?.nameSeries
            binding.tvVolumeStorage.text = data?.idStorage?.volumeItem.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            ItemStorageScreenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listStorageUser?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(
            listStorageUser?.get(position),
            listener
        )
    }
}