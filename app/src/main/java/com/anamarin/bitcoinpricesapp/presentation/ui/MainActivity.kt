package com.anamarin.bitcoinpricesapp.presentation.ui

import android.os.Bundle
import javax.inject.Inject
import dagger.android.AndroidInjection
import com.anamarin.bitcoinpricesapp.R
import androidx.appcompat.app.AppCompatActivity
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.core.utils.CHART_NAME
import com.anamarin.bitcoinpricesapp.core.utils.LifeDisposable
import com.anamarin.bitcoinpricesapp.presentation.viewmodels.MainViewModel
import com.anamarin.bitcoinpricesapp.core.utils.viewModelUtil.buildViewModel
import com.anamarin.bitcoinpricesapp.core.utils.viewModelUtil.AppViewModelFactory

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: AppViewModelFactory
    private val viewModel: MainViewModel by lazy { buildViewModel<MainViewModel>(factory) }

    private val disposable: LifeDisposable = LifeDisposable(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
    }

    override fun onResume() {
        super.onResume()

        disposable add viewModel.getBitcoinInfo(1, WEEK_PERIOD, CHART_NAME)
            .subscribe()
    }

}
