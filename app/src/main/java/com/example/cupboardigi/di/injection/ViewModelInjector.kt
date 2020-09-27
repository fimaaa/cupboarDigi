package com.example.cupboardigi.di.injection

import com.example.cupboardigi.di.module.BasicNetworkModule
import com.example.cupboardigi.ui.itemlist.ItemListViewModel
import com.example.cupboardigi.ui.menu.ContainerMenuViewModel
import com.example.cupboardigi.ui.menu.board.MenuBoardViewModel
import com.example.cupboardigi.ui.myitem.MyItemViewModel
import com.example.cupboardigi.ui.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(BasicNetworkModule::class)])
interface  ViewModelInjector {

    fun inject(containerMenuViewModel: ContainerMenuViewModel)
    fun inject(splashViewModel: SplashViewModel)
    fun inject(menuBoardViewModel: MenuBoardViewModel)
    fun inject(itemListViewModel: ItemListViewModel)
    fun inject(myItemViewModel: MyItemViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: BasicNetworkModule): Builder
    }
}