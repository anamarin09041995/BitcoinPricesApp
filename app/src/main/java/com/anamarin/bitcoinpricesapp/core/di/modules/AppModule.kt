package com.anamarin.bitcoinpricesapp.core.di.modules

import android.content.Context
import com.anamarin.bitcoinpricesapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton

@Module
class AppModule{

    @Provides
    fun provideContext(context: App) : Context {
        return  context
    }
}
