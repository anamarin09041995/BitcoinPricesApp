package com.anamarin.bitcoinpricesapp.core.di.modules

import com.anamarin.bitcoinpricesapp.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract  fun binMainActivity(): MainActivity
}