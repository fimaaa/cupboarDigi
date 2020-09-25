package com.example.cupboardigi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.cupboardigi.R
import com.example.cupboardigi.data.model.item.ItemSeries
import com.example.cupboardigi.databinding.ItemListitemCardBinding
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class AdapterSeries(
    private val listenerEditItem: (ItemSeries?) -> Unit
) : RecyclerView.Adapter<AdapterSeries.ViewHolder>() {
    private var listSeries: List<ItemSeries?>? = listOf()
    fun setListSeries(listSeries: List<ItemSeries?>?) {
        this.listSeries = listSeries
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: ItemListitemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindItem(
            data: ItemSeries?,
            listener: (ItemSeries?) -> Unit
        ) {
            binding.tvNameItem.text = data?.nameSeries
            binding.tvTypeItem.text = data?.typeItem?.nameType
            binding.tvVolumeItem.text = "${data?.volume} Volume"
            binding.tvStatusendItem.text = "Finished"
            binding.tvStatusendItem.visibility = if (data?.isEnd == true) {
                View.VISIBLE
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
            listSeries?.get(position),
            listenerEditItem
        )
    }

    override fun getItemCount(): Int = listSeries?.size ?: 0

}