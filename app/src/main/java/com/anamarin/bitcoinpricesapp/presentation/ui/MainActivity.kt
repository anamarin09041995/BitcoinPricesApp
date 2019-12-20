package com.anamarin.bitcoinpricesapp.presentation.ui

//import com.anamarin.bitcoinpricesapp.core.di.components.DaggerAppComponent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anamarin.bitcoinpricesapp.R
import com.anamarin.bitcoinpricesapp.core.utils.CHART_NAME
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: BitcoinInfoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

    }


    //override fun activityInjector(): AndroidInjector<Activity> = activityInjector()
}
