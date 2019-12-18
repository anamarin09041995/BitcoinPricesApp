package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.core.utils.getTestBitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.core.utils.getTestBitcoinInfoModel
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

class GetBitcoinInfoUseCaseTest {

    private lateinit var repository: BitcoinInfoRepository

    private lateinit var useCase: GetBitcoinInfoUsecase

    private val quantity = 2

    private val period: String = WEEK_PERIOD

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetBitcoinInfoUsecase(repository)
    }

    @Test
    fun callRepositoryMethodWhenUsecaseIsInvoke() {
        useCase.call(quantity, period)
        verify(repository).fetchBitcoinInfo(Mockito.anyInt(), anyString())
    }

    @Test
    fun getBitcoinInfoSuccessfully() {

        val model = getTestBitcoinInfoModel()
        val entity = getTestBitcoinInfoEntity(model)

        whenever(repository.fetchBitcoinInfo(Mockito.anyInt(), anyString())).thenReturn(
            Success(
                model
            )
        )

        val result = useCase.call(quantity, period)

        verify(repository).fetchBitcoinInfo(quantity,period)
        verifyNoMoreInteractions(repository)

        assert(result is Success)
        assertEquals((result as Success<BitcoinInfoEntity>).data, entity)
    }

    @Test
    fun getBitcoinInfoFailure() {
        whenever(repository.fetchBitcoinInfo(quantity, period)).thenReturn(
            Failure(Exception())
        )
        val result = useCase.call(quantity, period)

        verify(repository).fetchBitcoinInfo(quantity, period)
        verifyNoMoreInteractions(repository)

        assertEquals(result, (result as Failure))

    }
}