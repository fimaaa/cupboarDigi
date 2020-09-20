package com.example.cupboardigi.ui.menu.board

import androidx.lifecycle.Observer
import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.data.model.item.ItemScreen
import com.example.cupboardigi.databinding.FragmentMenuBoardBinding
import com.example.cupboardigi.ui.menu.ContainerMenuFragment
import com.example.cupboardigi.util.Dialog
import com.example.cupboardigi.util.Loading
import com.example.cupboardigi.util.showToast
import org.jetbrains.anko.sdk27.coroutines.onClick

class MenuBoardFragment : BaseFragment<FragmentMenuBoardBinding, MenuBoardViewModel>(), MenuBoardNavigator {
    override fun setLayout(): Int = R.layout.fragment_menu_board
    override fun getViewModelClass() = MenuBoardViewModel::class

    override fun onInitialization() {
        super.onInitialization()
        viewModel.navigator = this
    }

    override fun onObserveAction() {
        viewModel.itemCupBoard.observe(this, Observer {
            if(it.isNotEmpty()){
                viewModel.adapter.clearData()
                val listScreen = it[0].listScreen
                for(i in listScreen.indices){
                    val liveData = viewModel.getItemScreen(listScreen[i].idScreen)
                    liveData.observe(this, Observer { it2 ->
                        val screen = it2.screen
                        screen.itemStorages = it2.storageUser
                        viewModel.adapter.addData(screen)
                        liveData.removeObservers(this)
                    })
                }
            } else {
                viewModel.addBoard()
            }
        })
    }

    override fun onReadyAction() {
        dataBinding.btnProfileBoard.onClick {
            (parentFragment as ContainerMenuFragment).goToPage(0)
        }
        dataBinding.btnListBoard.onClick {
            (parentFragment as ContainerMenuFragment).goToPage(2)
        }
    }

    override fun addScreen() {
        Dialog.dialogAddScreen(
            requireContext(),
            viewModel.itemCupBoard.value?.get(0)?.board?.idBoard ?: 0L
        ) {
            Loading.showLoadingGif(requireContext())
            viewModel.addScreen(it)
        }
    }

    override fun editScreen(
        position: Int,
        itemScreen: ItemScreen
    ) {
        requireContext().showToast("Edit Screen")
    }
}