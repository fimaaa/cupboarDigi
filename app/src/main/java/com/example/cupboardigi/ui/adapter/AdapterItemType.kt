package com.example.cupboardigi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.cupboardigi.data.model.item.ItemType
import com.example.cupboardigi.databinding.ItemTypeBinding

class AdapterItemType(private val listType: List<ItemType>): BaseAdapter() {
    override fun getCount(): Int = listType.size

    override fun getItem(position: Int): ItemType = listType[position]

    override fun getItemId(position: Int): Long = listType[position].idType

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemTypeBinding.inflate(
            LayoutInflater.from(parent?.context),
            parent,
            false
        )
        binding.nameType = listType[position].nameType
        return binding.root
    }
}