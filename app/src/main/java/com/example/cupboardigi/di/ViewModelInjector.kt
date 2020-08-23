package com.example.cupboardigi.di

import com.example.cupboardigi.di.module.BasicNetworkModule
import com.example.cupboardigi.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(BasicNetworkModule::class)])
interface  ViewModelInjector {

    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: BasicNetworkModule): Builder
    }
}