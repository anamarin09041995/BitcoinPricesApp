package com.anamarin.bitcoinpricesapp.presentation.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.usescases.GetBitcoinInfoUsecase
import javax.inject.Inject

class MainViewModel @Inject constructor(private val bitcoinUseCase: GetBitcoinInfoUsecase) : ViewModel() {

    val liveDataBitcoinEntit = MutableLiveData<BitcoinInfoEntity>()

    lateinit var data: BitcoinInfoEntity // Test purposes

    @SuppressLint("CheckResult")
    fun getBitcoinInfo(quantity: Int, period: String, chartName: String) {
        bitcoinUseCase.callSingle(quantity, period, chartName)
            .subscribe({
                data = (it as Success<BitcoinInfoEntity>).data
                liveDataBitcoinEntit.value = ((it as Success<BitcoinInfoEntity>).data)
            }, {
                it.printStackTrace()

            })

    }
}