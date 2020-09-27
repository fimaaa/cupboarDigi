package com.example.cupboardigi.ui.myitem

import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.FragmentMyitemListBinding
import kotlin.reflect.KClass

class MyItemFragment : BaseFragment<FragmentMyitemListBinding, MyItemViewModel>(),
    MyItemNavigator {
    override fun setLayout(): Int = R.layout.fragment_myitem_list

    override fun getViewModelClass(): KClass<MyItemViewModel> = MyItemViewModel::class

    override fun onReadyAction() {

    }

}