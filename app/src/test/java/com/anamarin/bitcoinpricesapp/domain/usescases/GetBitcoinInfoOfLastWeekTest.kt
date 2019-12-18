package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.result.Results
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

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
        useCase.call()
        verify(repository).fetchBitcoinInfo(1, WEEK_PERIOD)
    }

    @Test
    fun getBitcoinInfoSuccessfull(){
        val bitcoinInfoModel = BitcoinInfoModel(name = "market-prices", unit = "USD", values = (0..6).map {  BitcoinCoordinatesModel(it.toDouble(), it.toDouble()) })
        val bitcoinInfoEntity = BitcoinInfoEntity(bitcoinInfoModel)

        whenever(repository.fetchBitcoinInfo(Mockito.anyInt(), anyString())).thenReturn(
            Results.Success(
                bitcoinInfoModel
            )
        )

        val result = useCase.call()

        verify(repository).fetchBitcoinInfo(1, WEEK_PERIOD)

        assert(result is Results.Success)
        assertEquals((result as Results.Success<BitcoinInfoEntity>).data, bitcoinInfoEntity)
    }
}