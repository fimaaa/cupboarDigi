package com.example.cupboardigi.ui.menu.account

import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.FragmentMenuAccountBinding
import kotlin.reflect.KClass

class MenuAccountFragment : BaseFragment<FragmentMenuAccountBinding, MenuAccountViewModel>() {
    override fun setLayout(): Int = R.layout.fragment_menu_account

    override fun getViewModelClass(): KClass<MenuAccountViewModel> = MenuAccountViewModel::class

    override fun onObserveAction() {
    }

    override fun onReadyAction() {
    }
}