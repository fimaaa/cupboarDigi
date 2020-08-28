package com.example.cupboardigi.ui.menu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cupboardigi.R
import com.example.cupboardigi.animation.CubeInViewPager2
import com.example.cupboardigi.base.BaseContainerFragment
import com.example.cupboardigi.ui.menu.account.MenuAccountFragment
import com.example.cupboardigi.ui.menu.board.MenuBoardFragment
import com.example.cupboardigi.ui.menu.list.MenuListFragment
import kotlinx.android.synthetic.main.fragment_container_menu.*

class ContainerMenuFragment : BaseContainerFragment() {

    override fun setLayout(): Int = R.layout.fragment_container_menu

    override fun onInitialization() {
        super.onInitialization()
        vp_container_menu.setPageTransformer(CubeInViewPager2())
        vp_container_menu.adapter = ContainerMenuAdapter(requireActivity())
        vp_container_menu.setCurrentItem(1, false)
        vp_container_menu.offscreenPageLimit = 3
    }

    private inner class ContainerMenuAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> {
                    MenuAccountFragment()
                }
                1 ->{
                    MenuBoardFragment()
                }
                else -> {
                    MenuListFragment()
                }
            }
        }
    }
}