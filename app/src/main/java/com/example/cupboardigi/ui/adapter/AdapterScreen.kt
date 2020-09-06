package com.example.cupboardigi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cupboardigi.data.model.item.ItemScreen
import com.example.cupboardigi.data.model.item.ItemStorageUser
import com.example.cupboardigi.databinding.ItemAddItemBinding
import com.example.cupboardigi.databinding.ItemScreenBoardBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class AdapterScreen(
    private val listenerEdit: (Int?) -> Unit,
    private val listenerAdd: () -> Unit,
    private val listenerItemClicked: (ItemStorageUser?) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val VIEWTYPE_SCREEN = 1
        const val VIEWTYPE_ADD = 0
    }
    private var listScreen = mutableListOf<ItemScreen>()
    fun changeData(listScreen: List<ItemScreen>){
        this.listScreen = listScreen.toMutableList()
        notifyDataSetChanged()
    }

    fun clearData(){
        listScreen.clear()
        notifyDataSetChanged()
    }

    fun addData(itemScreen: ItemScreen){
        listScreen.add(itemScreen)
        notifyDataSetChanged()
    }
    class ViewHolderScreenStorage(private val binding: ItemScreenBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            listStorages:List<ItemStorageUser?>?,
            listenerEdit: (Int?) -> Unit,
            listenerItemClicked: (ItemStorageUser?) -> Unit
        ) {
            val adapter = AdapterStorageScreen(listenerItemClicked)
            adapter.setListReward(listStorages)
            binding.rcvItemScreen.adapter = adapter
        }
    }

    class ViewHolderAddRecyclerView(private val binding: ItemAddItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(
            listenerAdd: () -> Unit
        ) {
            binding.ivRecyclerviewAdd.onClick {
                listenerAdd.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if(position >= listScreen.size){
            VIEWTYPE_ADD
        }else{
            VIEWTYPE_SCREEN
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when(viewType){
            VIEWTYPE_ADD -> {
                ViewHolderAddRecyclerView(
                    ItemAddItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                ViewHolderScreenStorage(
                    ItemScreenBoardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    override fun getItemCount(): Int = listScreen.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            VIEWTYPE_ADD ->{
                (holder as ViewHolderAddRecyclerView).bind(listenerAdd)
            }
            else -> {
                (holder as ViewHolderScreenStorage).bind(
                    listScreen[position].itemStorages,
                    listenerEdit,
                    listenerItemClicked
                )
            }
        }
    }
}