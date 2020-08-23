package com.example.cupboardigi.ui.splash

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
        viewModel.displaySplash()
    }

    override fun goToLogin() {
    }

    override fun goToHome() {
        val directions = SplashFragmentDirections.actionSplashFragmentToMainFragment()
        mainNavController?.navigate(directions)
    }
}