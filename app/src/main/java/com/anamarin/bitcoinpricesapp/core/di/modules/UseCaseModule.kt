package com.anamarin.bitcoinpricesapp.core.di.modules

import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import com.anamarin.bitcoinpricesapp.domain.usescases.GetBitcoinInfoUsecase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetBitcoinInfoUseCase(repository: BitcoinInfoRepository): GetBitcoinInfoUsecase {
        return GetBitcoinInfoUsecase(repository)
    }

}