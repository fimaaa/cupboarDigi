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

    val adapterScreen = AdapterScreen(
        {

        },
        {

        },
        {

        })

    override fun onObserveAction() {
        viewModel.itemCupBoard.observe(this, Observer {
            adapterScreen.addData(it[0].screen)
            for(i in it[0].screen.indices){
                println("TAG screenID = ${it[0].screen[i].idScreen}")
                println("TAG screenID = ${it[0].screen[i].itemStorages?.size}")
                for(j in 0 until (it[0].screen[i].itemStorages?.size?:0)){
                    println("TAG nameStorage = ${it[0].screen[i].itemStorages?.get(j)?.idStorage?.typeItem?.nameSeries} " +
                            "number ${it[0].screen[i].itemStorages?.get(j)?.idStorage?.typeItem?.volume}")
                }
            }
            viewModel.adapter = adapterScreen
        })
    }

    override fun onReadyAction() {
    }
}