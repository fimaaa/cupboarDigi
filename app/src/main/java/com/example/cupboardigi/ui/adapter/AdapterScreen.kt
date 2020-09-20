package com.example.cupboardigi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.cupboardigi.R
import com.example.cupboardigi.data.model.item.ItemScreen
import com.example.cupboardigi.data.model.item.ItemStorageUser
import com.example.cupboardigi.databinding.ItemAddItemBinding
import com.example.cupboardigi.databinding.ItemScreenBoardBinding
import org.jetbrains.anko.sdk27.coroutines.onClick


class AdapterScreen(
    private val listenerEdit: (Int, ItemScreen) -> Unit,
    private val listenerAdd: () -> Unit,
    private val listenerItemClicked: (ItemStorageUser?) -> Unit,
    private val listenerAddItem: () -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEWTYPE_SCREEN = 1
        const val VIEWTYPE_ADD = 0
    }

    private var listScreen = mutableListOf<ItemScreen>()

    fun clearData() {
        listScreen.clear()
        notifyDataSetChanged()
    }

    fun addData(itemScreen: ItemScreen) {
        listScreen.add(itemScreen)
        notifyDataSetChanged()
    }

    fun editData(position: Int, itemScreen: ItemScreen){
        listScreen[position] = itemScreen
        notifyItemChanged(position)
    }

    class ViewHolderScreenStorage(private val binding: ItemScreenBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            screen: ItemScreen?,
            listenerEdit: () -> Unit,
            listenerItemClicked: (ItemStorageUser?) -> Unit,
            listenerAddItem: () -> Unit
        ) {
            setCover(
                binding.root.context,
                binding.tvNameScreen,
                screen?.isShown == true
            )
            binding.tvNameScreen.text = screen?.nameScreen
            val adapter = AdapterStorageScreen(
                listenerItemClicked,
                listenerAddItem
            )
            binding.btnOptionScreem.onClick {
                listenerEdit.invoke()
            }
            binding.btnShownScreen.onClick {
                screen?.isShown = !(screen?.isShown?:true)
                setCover(
                    binding.root.context,
                    binding.tvNameScreen,
                    screen?.isShown == true
                )
            }
            adapter.setListReward(screen?.itemStorages)
            binding.rcvItemScreen.adapter = adapter
        }

        private fun setCover(
            mContext: Context,
            textView: View,
            isShown: Boolean
        ){
            if(isShown) {
                val animation1: Animation =
                    AnimationUtils.loadAnimation(mContext, R.anim.openscreen)
                textView.startAnimation(animation1)
            } else {
                val animation1: Animation =
                    AnimationUtils.loadAnimation(mContext, R.anim.closescreen)
                textView.startAnimation(animation1)
            }
        }
    }

    class ViewHolderAddRecyclerView(private val binding: ItemAddItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            listenerAdd: () -> Unit
        ) {
            val params = binding.root.layoutParams
            params.height = 200
            binding.ivRecyclerviewAdd.onClick {
                listenerAdd.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position >= listScreen.size) {
            VIEWTYPE_ADD
        } else {
            VIEWTYPE_SCREEN
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (viewType) {
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
        when (holder.itemViewType) {
            VIEWTYPE_ADD -> {
                (holder as ViewHolderAddRecyclerView).bind(listenerAdd)
            }
            else -> {
                val screen = listScreen[position]
                (holder as ViewHolderScreenStorage).bind(
                    screen,
                    {
                        listenerEdit.invoke(
                            position,
                            screen
                        )
                    },
                    listenerItemClicked,
                    listenerAddItem
                )
            }
        }
    }
}