package com.example.cupboardigi.ui.menu

import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.FragmentMenuBinding
import kotlin.reflect.KClass

class MenuFragment : BaseFragment<FragmentMenuBinding,MenuViewModel>() {

    override fun setLayout(): Int = R.layout.fragment_menu

    override fun getViewModelClass(): KClass<MenuViewModel> = MenuViewModel::class

    override fun onInitialization() {
        super.onInitialization()

    }

    override fun onReadyAction() {
        viewModel.getStorage()
    }

}