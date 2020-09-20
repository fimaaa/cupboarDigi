package com.example.cupboardigi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cupboardigi.data.model.item.ItemStorageUser
import com.example.cupboardigi.databinding.ItemAddItemBinding
import com.example.cupboardigi.databinding.ItemStorageScreenBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class AdapterStorageScreen(
    private val listener: (ItemStorageUser?) -> Unit,
    private val listenerAddItem: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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

    class ViewHolderAddRecyclerView(private val binding: ItemAddItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            listenerAddItem: () -> Unit
        ) {
            val params = binding.root.layoutParams
            params.width = 100
            binding.ivRecyclerviewAdd.onClick {
                listenerAddItem.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position >= (listStorageUser?.size ?: 0)) {
            AdapterScreen.VIEWTYPE_ADD
        } else {
            AdapterScreen.VIEWTYPE_SCREEN
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when(viewType){
            AdapterScreen.VIEWTYPE_SCREEN -> {
                ViewHolder(
                    ItemStorageScreenBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                ViewHolderAddRecyclerView(
                    ItemAddItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }


    override fun getItemCount(): Int = (listStorageUser?.size ?: 0) + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            AdapterScreen.VIEWTYPE_SCREEN -> {
                (holder as ViewHolder).bindItem(
                    listStorageUser?.get(position),
                    listener
                )
            }
            else -> {
                (holder as ViewHolderAddRecyclerView).bind(
                    listenerAddItem
                )
            }
        }
    }
}