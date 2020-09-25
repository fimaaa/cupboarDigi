package com.example.cupboardigi.ui.itemlist

import android.view.View
import androidx.lifecycle.Observer
import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.FragmentItemlistBinding
import com.example.cupboardigi.ui.adapter.AdapterSeries
import com.example.cupboardigi.util.Dialog
import com.example.cupboardigi.util.Loading
import com.example.cupboardigi.util.showToast
import org.jetbrains.anko.sdk27.coroutines.onClick
import kotlin.reflect.KClass


class ItemListFragment : BaseFragment<FragmentItemlistBinding, ItemListViewModel>(),
    ItemListNavigator {
    override fun setLayout(): Int = R.layout.fragment_itemlist

    override fun getViewModelClass(): KClass<ItemListViewModel> = ItemListViewModel::class

    override fun onInitialization() {
        viewModel.navigator = this
    }

    override fun onReadyAction() {
        dataBinding.layoutDatanotfoundItmelist.btnFunctionbutton.onClick {
            addSeries()
        }
    }

    override fun onObserveAction() {
        viewModel.allSeries.observe(this, Observer {
            if (it.isEmpty()) {
                dataBinding.layoutDatanotfoundItmelist.root.visibility = View.VISIBLE
            } else {
                val adapter = AdapterSeries{ series ->
                    requireContext().showToast("Edit Data ${series?.nameSeries}")
                }
                adapter.setListSeries(it)
                dataBinding.adapter = adapter
                dataBinding.layoutDatanotfoundItmelist.root.visibility = View.GONE
            }
        })
        viewModel.allTypeItem.observe(this, Observer {
            println("TAG Size TypeItem = ${it.size}")
        })
    }

    override fun addSeries() {
        Dialog.dialogAddSeries(
            requireContext(),
            viewModel.allTypeItem.value,
            {
                Loading.showLoadingGif(requireContext())
                viewModel.addSeries(it)
            },
            {

            }
        )
    }
}