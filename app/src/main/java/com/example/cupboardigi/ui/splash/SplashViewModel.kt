package com.example.cupboardigi.ui.splash

import android.app.Application
import android.os.Handler
import com.example.cupboardigi.base.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel<SplashNavigator>(application) {
    fun displaySplash() {
        val handler = Handler()
        handler.postDelayed(
            { // Do something after 3s = 3000ms
                navigator?.goToHome()
            },
            3000
        )
    }
}