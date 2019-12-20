package com.anamarin.bitcoinpricesapp.core.di.components

import com.anamarin.bitcoinpricesapp.App
import com.anamarin.bitcoinpricesapp.core.di.modules.ActivityModule
import com.anamarin.bitcoinpricesapp.core.di.modules.AppModule
import com.anamarin.bitcoinpricesapp.core.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,  RepositoryModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<App>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app:App): Builder

        fun build(): AppComponent
    }
}