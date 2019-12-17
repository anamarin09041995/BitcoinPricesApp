package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class GetBitcoinPricesOfLasteWeekTest{

    private lateinit var repository: BitcoinInfoRepository

    private lateinit var useCase: GetBitcoinInfoOfLastWeek

    @Before
    fun setUp(){
        repository = mock()
        useCase = GetBitcoinInfoOfLastWeek(repository)
    }

    @Test
    fun callRepositoryMethodWhenUsecaseIsInvoke(){
        val quantity = 1
        useCase.call()
        verify(repository).fetchBitcoinInfo(quantity, WEEK_PERIOD)
    }

}