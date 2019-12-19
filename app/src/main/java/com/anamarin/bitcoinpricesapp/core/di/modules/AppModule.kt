package com.anamarin.bitcoinpricesapp.core.di.modules

import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{

    @Provides
    @Singleton
    fun provideContext(context: Context) : Application{
        return  context.applicationContext as Application
    }
}
