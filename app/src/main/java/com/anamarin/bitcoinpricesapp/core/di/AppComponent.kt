package com.anamarin.bitcoinpricesapp.core.di

import com.anamarin.bitcoinpricesapp.App
import com.anamarin.bitcoinpricesapp.core.di.modules.AppModule
import com.anamarin.bitcoinpricesapp.core.di.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,  RepositoryModule::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(appModule: AppModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder

    }

}