package com.example.cupboardigi.base

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import com.example.cupboardigi.di.injection.DaggerViewModelInjector
import com.example.cupboardigi.di.injection.ViewModelInjector
import com.example.cupboardigi.di.module.BasicNetworkModule
import com.example.cupboardigi.ui.itemlist.ItemListViewModel
import com.example.cupboardigi.ui.menu.ContainerMenuViewModel
import com.example.cupboardigi.ui.menu.board.MenuBoardViewModel
import com.example.cupboardigi.ui.myitem.MyItemViewModel
import com.example.cupboardigi.ui.splash.SplashViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {

    private var mNavigator: WeakReference<N>? = null
    private val mCompositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
    }

    var compositeDisposable = mCompositeDisposable

    var navigator: N?
        get() {
            return mNavigator?.get()
        }
        set(value) {
            mNavigator = WeakReference<N>(value)
        }

    private val injectorBasic: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(BasicNetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ContainerMenuViewModel -> injectorBasic.inject(this)
            is SplashViewModel -> injectorBasic.inject(this)
            is MenuBoardViewModel -> injectorBasic.inject(this)
            is ItemListViewModel -> injectorBasic.inject(this)
            is MyItemViewModel -> injectorBasic.inject(this)
        }
    }
}