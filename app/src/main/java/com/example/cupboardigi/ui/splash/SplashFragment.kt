package com.example.cupboardigi.ui.splash

import androidx.lifecycle.Observer
import com.example.cupboardigi.R
import com.example.cupboardigi.base.BaseFragment
import com.example.cupboardigi.databinding.FragmentSplashscreenBinding
import kotlin.reflect.KClass

class SplashFragment : BaseFragment<FragmentSplashscreenBinding, SplashViewModel>(),
    SplashNavigator {
    override fun setLayout(): Int = R.layout.fragment_splashscreen

    override fun getViewModelClass(): KClass<SplashViewModel> = SplashViewModel::class

    override fun onInitialization() {
        super.onInitialization()
        viewModel.navigator = this
    }

    override fun onReadyAction() {
        viewModel.itemType.observe(this, Observer {
            viewModel.itemType.removeObservers(this)
            if (it.isNullOrEmpty()) {
                println("TAG AddItem")
                viewModel.addItemType()
            } else {
                println("TAG Splash")
                viewModel.displaySplash()
            }
        })
    }

    override fun goToLogin() {
    }

    override fun goToHome() {
        val directions = SplashFragmentDirections.actionSplashFragmentToMainFragment()
        mainNavController?.navigate(directions)
    }
}