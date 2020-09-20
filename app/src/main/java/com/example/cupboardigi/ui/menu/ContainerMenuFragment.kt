package com.example.cupboardigi.ui.menu

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
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
        vp_container_menu.adapter = ContainerMenuAdapter(this)
        vp_container_menu.setCurrentItem(1, false)
        vp_container_menu.offscreenPageLimit = 3
        vp_container_menu.isUserInputEnabled = false
        vp_container_menu.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                vp_container_menu.isUserInputEnabled = position != 1
            }
        })

    }

    private class ContainerMenuAdapter(parentFragment: Fragment) :
        FragmentStateAdapter(parentFragment) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    MenuAccountFragment()
                }
                1 -> {
                    MenuBoardFragment()
                }
                else -> {
                    MenuListFragment()
                }
            }
        }
    }

    fun goToPage(position: Int) {
        vp_container_menu.setCurrentItem(position, true)
    }


}