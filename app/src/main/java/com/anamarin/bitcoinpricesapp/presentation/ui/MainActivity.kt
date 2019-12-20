package com.anamarin.bitcoinpricesapp.presentation.ui

//import com.anamarin.bitcoinpricesapp.core.di.components.DaggerAppComponent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.anamarin.bitcoinpricesapp.R
import com.anamarin.bitcoinpricesapp.core.utils.CHART_NAME
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import com.anamarin.bitcoinpricesapp.domain.usescases.GetBitcoinInfoUsecase
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var usecase: GetBitcoinInfoUsecase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

    }


    override fun onResume() {
        super.onResume()



        usecase.callSingle(1, WEEK_PERIOD, CHART_NAME)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
//                LiveData<BitcoinInfoEntity> data ()
                val a = it
            }
            .doOnError {
                val b = 0
            }
            .subscribe()

    }


}
