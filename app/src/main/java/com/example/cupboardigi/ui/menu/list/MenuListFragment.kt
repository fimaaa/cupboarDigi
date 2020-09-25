package com.example.cupboardigi.ui.menu.list

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.FragmentMenuListBinding
import com.example.cupboardigi.ui.menu.ContainerMenuFragmentDirections
import kotlin.reflect.KClass

class MenuListFragment : BaseFragment<FragmentMenuListBinding, MenuListViewModel>(), MenuListNavigator {
    private val menuController: NavController? by lazy { activity?.findNavController(R.id.nav_main) }

    override fun setLayout(): Int = R.layout.fragment_menu_list

    override fun getViewModelClass(): KClass<MenuListViewModel> = MenuListViewModel::class

    override fun onInitialization() {
        super.onInitialization()
        viewModel.navigator = this
    }

    override fun onObserveAction() {
    }

    override fun onReadyAction() {

    }

    override fun goToListItem() {
        val direction = ContainerMenuFragmentDirections.actionMainFragmentToItemListFragment()
        menuController?.navigate(direction)
    }
}