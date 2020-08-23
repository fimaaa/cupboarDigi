package com.example.cupboardigi.base

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import com.example.cupboardigi.di.DaggerViewModelInjector
import com.example.cupboardigi.di.ViewModelInjector
import com.example.cupboardigi.di.module.BasicNetworkModule
import com.example.cupboardigi.ui.menu.MenuViewModel
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
            mNavigator = WeakReference<N>(value) ?: null
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
            is MenuViewModel -> injectorBasic.inject(this)
            is SplashViewModel -> injectorBasic.inject(this)
        }
    }
}