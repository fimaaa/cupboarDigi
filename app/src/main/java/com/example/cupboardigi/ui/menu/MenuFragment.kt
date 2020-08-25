package com.example.cupboardigi.ui.menu

import androidx.lifecycle.Observer
import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.data.model.data.Resource
import com.example.cupboardigi.databinding.FragmentMenuBinding
import kotlin.reflect.KClass

class MenuFragment : BaseFragment<FragmentMenuBinding,MenuViewModel>() {

    override fun setLayout(): Int = R.layout.fragment_menu

    override fun getViewModelClass(): KClass<MenuViewModel> = MenuViewModel::class

    override fun onObserveAction() {
        super.onObserveAction()
        viewModel.itemStorageItem.observe(this, Observer {
            for(x in it.indices){
                println("TAG Storage ke-$x")
                println("TAG StorageID = ${it[x].id}")
                println("TAG item Type= ${it[x].typeItem}")
                println("TAG nama Type= ${it[x].nameType}")
                println("TAG Storage = ${it[x].nameItem}")

            }
        })
        viewModel.responseStorage.observe(this, Observer {
            when(it.status){
                Resource.Status.LOADING -> {
                }
                Resource.Status.SUCCESS -> {
                }
                Resource.Status.ERROR -> {
                }
            }
        })
    }

    override fun onReadyAction() {
        viewModel.getStorage()
    }

}