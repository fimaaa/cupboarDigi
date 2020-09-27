package com.example.cupboardigi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.cupboardigi.R
import com.example.cupboardigi.data.model.item.ItemStorage
import com.example.cupboardigi.databinding.ItemListitemCardBinding
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class AdapterStorage(
    private val listenerEditItem: (ItemStorage?) -> Unit
) : RecyclerView.Adapter<AdapterStorage.ViewHolder>() {
    private var listMyItem: List<ItemStorage?>? = listOf()
    fun setListSeries(listMyItem: List<ItemStorage?>?) {
        this.listMyItem = listMyItem
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: ItemListitemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindItem(
            data: ItemStorage?,
            listener: (ItemStorage?) -> Unit
        ) {
            binding.tvNameItem.text = data?.typeItem?.nameSeries
            binding.tvTypeItem.text = data?.typeItem?.typeItem?.nameType
            binding.tvVolumeItem.text = "${data?.volumeItem} Volume"
            binding.tvStatusendItem.text = "Finished"
            binding.tvStatusendItem.visibility = if (data?.typeItem?.isEnd == true) {
                if (data.volumeItem == data.typeItem.volume) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            } else {
                View.GONE
            }
            var covering = true
            binding.root.onClick {
                val cover = binding.ivCoverItem
                if (!covering) {
                    val animationIn: Animation =
                        AnimationUtils.loadAnimation(binding.root.context, R.anim.in_from_top)
                    covering = true
                    cover.startAnimation(animationIn)
                } else {
                    val animationIn: Animation =
                        AnimationUtils.loadAnimation(binding.root.context, R.anim.out_from_bottom)
                    animationIn.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            cover.visibility = View.GONE
                        }

                        override fun onAnimationRepeat(animation: Animation?) {
                        }

                    })
                    covering = false
                    cover.startAnimation(animationIn)
                }
            }
            binding.root.onLongClick {
                listener.invoke(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListitemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(
            listMyItem?.get(position),
            listenerEditItem
        )
    }

    override fun getItemCount(): Int = listMyItem?.size ?: 0

}