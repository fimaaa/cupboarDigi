package com.example.cupboardigi.ui.menu.board

import androidx.lifecycle.Observer
import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.FragmentMenuBoardBinding
import com.example.cupboardigi.ui.adapter.AdapterScreen
import kotlin.reflect.KClass

class MenuBoardFragment : BaseFragment<FragmentMenuBoardBinding, MenuBoardViewModel>() {
    override fun setLayout(): Int = R.layout.fragment_menu_board
    override fun getViewModelClass(): KClass<MenuBoardViewModel> = MenuBoardViewModel::class

    override fun onObserveAction() {
        viewModel.itemCupBoard.observe(this, Observer {
            // Only Board 0
            println("TAG board = ${it.size}")
            if(it.isNotEmpty()){
                viewModel.getItem(it[0].listScreen)
            }
        })
    }

    override fun onReadyAction() {
    }
}