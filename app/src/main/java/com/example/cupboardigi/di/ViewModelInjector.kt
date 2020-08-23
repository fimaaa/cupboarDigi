package com.example.cupboardigi.di

import com.example.cupboardigi.di.module.BasicNetworkModule
import com.example.cupboardigi.ui.menu.MenuViewModel
import com.example.cupboardigi.ui.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(BasicNetworkModule::class)])
interface  ViewModelInjector {

    fun inject(menuViewModel: MenuViewModel)
    fun inject(splashViewModel: SplashViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: BasicNetworkModule): Builder
    }
}