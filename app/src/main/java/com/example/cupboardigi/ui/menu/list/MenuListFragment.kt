package com.example.cupboardigi.ui.menu.list

import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.FragmentMenuListBinding
import kotlin.reflect.KClass

class MenuListFragment : BaseFragment<FragmentMenuListBinding, MenuListViewModel>() {
    override fun setLayout(): Int = R.layout.fragment_menu_list

    override fun getViewModelClass(): KClass<MenuListViewModel> = MenuListViewModel::class

    override fun onObserveAction() {
    }

    override fun onReadyAction() {
    }
}