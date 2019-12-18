package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.result.Results.Success
import com.anamarin.bitcoinpricesapp.core.result.Results.Failure
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.core.utils.getBitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.core.utils.getBitcoinInfoModel
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import java.lang.Exception

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
    fun getBitcoinInfoSuccessfully(){

        val model = getBitcoinInfoModel()
        val entity = getBitcoinInfoEntity(model)

        whenever(repository.fetchBitcoinInfo(Mockito.anyInt(), anyString())).thenReturn(
            Success(
                model
            )
        )

        val result = useCase.call()

        verify(repository).fetchBitcoinInfo(1, WEEK_PERIOD)
        verifyNoMoreInteractions(repository)

        assert(result is Success)
        assertEquals((result as Success<BitcoinInfoEntity>).data, entity)
    }

    @Test
    fun getBitcoinInfoFailure(){
        whenever(repository.fetchBitcoinInfo(Mockito.anyInt(), anyString())).thenReturn(
            Failure(Exception())
        )

        val result = useCase.call()

        verify(repository).fetchBitcoinInfo(1, WEEK_PERIOD)
        verifyNoMoreInteractions(repository)

        assertEquals(result, (result as Failure))

    }
}