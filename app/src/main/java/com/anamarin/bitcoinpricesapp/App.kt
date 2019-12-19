package com.anamarin.bitcoinpricesapp

import android.app.Activity
import android.app.Application
import com.anamarin.bitcoinpricesapp.core.di.AppComponent
import com.anamarin.bitcoinpricesapp.core.di.modules.AppModule
import com.anamarin.bitcoinpricesapp.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector

@Suppress("DEPRECATION")
class App: Application(), HasActivityInjector {

    private val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule())
            .build()
    }


    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return  activityInjector()
    }

}
