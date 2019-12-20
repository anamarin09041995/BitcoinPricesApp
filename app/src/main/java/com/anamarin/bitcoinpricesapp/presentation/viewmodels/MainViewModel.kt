package com.anamarin.bitcoinpricesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.anamarin.bitcoinpricesapp.domain.usescases.GetBitcoinInfoUsecase
import javax.inject.Inject

class MainViewModel @Inject constructor(private val bitcoinUseCase: GetBitcoinInfoUsecase) : ViewModel(){

}