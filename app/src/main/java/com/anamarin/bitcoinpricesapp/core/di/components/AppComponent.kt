package com.anamarin.bitcoinpricesapp.core.di.components

import com.anamarin.bitcoinpricesapp.App
import com.anamarin.bitcoinpricesapp.core.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, RepositoryModule::class, ActivityModule::class, ViewModelModule::class, UseCaseModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }
}