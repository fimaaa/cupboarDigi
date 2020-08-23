package com.example.cupboardigi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.MainFragmentBinding
import kotlin.reflect.KClass

class MainFragment : BaseFragment<MainFragmentBinding,MainViewModel>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun setLayout(): Int = R.layout.main_fragment

    override fun getViewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onInitialization() {
        super.onInitialization()
        viewModel.getStorage()
    }

}