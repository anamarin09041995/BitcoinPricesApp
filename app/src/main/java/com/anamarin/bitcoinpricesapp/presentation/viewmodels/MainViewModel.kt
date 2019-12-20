package com.anamarin.bitcoinpricesapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.usescases.GetBitcoinInfoUsecase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(private val bitcoinUseCase: GetBitcoinInfoUsecase) : ViewModel() {

    private val liveDataBitcoinEntity = MutableLiveData<BitcoinInfoEntity>()

    fun getBitcoinInfo(quantity: Int, period: String, chartName: String): Single<Outcome<BitcoinInfoEntity>> {
        return bitcoinUseCase.callSingle(quantity, period, chartName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                liveDataBitcoinEntity.value = (it as Success).data
                Success(liveDataBitcoinEntity.value)
            }.doOnError {
                Failure(Exception())
            }
    }
}