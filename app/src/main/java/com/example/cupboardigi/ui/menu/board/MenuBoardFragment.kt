package com.example.cupboardigi.ui.menu.board

import androidx.lifecycle.Observer
import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.FragmentMenuBoardBinding
import kotlin.reflect.KClass

class MenuBoardFragment : BaseFragment<FragmentMenuBoardBinding, MenuBoardViewModel>() {
    override fun setLayout(): Int = R.layout.fragment_menu_board

    override fun getViewModelClass(): KClass<MenuBoardViewModel> = MenuBoardViewModel::class

    override fun onObserveAction() {
        viewModel.itemStorageItem.observe(this, Observer {
            for(x in it.indices){
                println("TAG Storage ke-$x")
                println("TAG StorageID = ${it[x].idStorage}")
                println("TAG item Type= ${it[x].typeItem}")
                println("TAG nama Type= ${it[x].typeItem.nameType}")
                println("TAG Storage = ${it[x].nameItem}")
            }
        })
    }

    override fun onReadyAction() {
    }
}