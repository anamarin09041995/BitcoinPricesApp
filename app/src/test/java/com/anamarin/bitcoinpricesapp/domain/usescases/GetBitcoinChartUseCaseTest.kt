package com.anamarin.bitcoinpricesapp.domain.usescases

import com.anamarin.bitcoinpricesapp.core.error.DatabaseException
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.core.utils.getTestBitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.core.utils.getTestBitcoinInfoModel
import com.anamarin.bitcoinpricesapp.data.repositories.CHART_NAME
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.lang.Exception

class GetBitcoinInfoUseCaseTest {

    private lateinit var repository: BitcoinInfoRepository

    private lateinit var useCase: GetBitcoinInfoUsecase

    private val quantity = 2

    private val period: String = WEEK_PERIOD

    private val name: String = CHART_NAME

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetBitcoinInfoUsecase(repository)
    }

    @Test
    fun getBitcoinInfoSuccessfully() {
        val bitcoinInfoModel = getTestBitcoinInfoModel()
        val bitcoinInfoEntity = getTestBitcoinInfoEntity(bitcoinInfoModel)
        val singleBitcoinInfoModel = Single.just(Success(bitcoinInfoModel))

        Mockito.doReturn(singleBitcoinInfoModel).`when`(repository).fetchBitcoinInfoSingle(quantity, period, name)

        val testObserver: TestObserver<Outcome<BitcoinInfoEntity>> = useCase.callSingle(quantity, period, name).test()

        verify(repository).fetchBitcoinInfoSingle(quantity,period,name)

        testObserver.assertValues(Success(bitcoinInfoEntity))

        testObserver.dispose()
    }

    @Test
    fun getBitcoinInfoFailure(){
        val mockLocalFailure = Single.error<Exception>(DatabaseException())

        Mockito.doReturn(mockLocalFailure).`when`(repository).fetchBitcoinInfoSingle(quantity, period, name)

        val testObserver: TestObserver<Outcome<BitcoinInfoEntity>> = useCase.callSingle(quantity, period, name).test()

        verify(repository).fetchBitcoinInfoSingle(quantity, period, name)

        testObserver.assertValue { it is Failure }

        testObserver.dispose()
    }
}