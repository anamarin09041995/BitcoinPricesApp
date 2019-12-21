package com.anamarin.bitcoinpricesapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatus
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.core.utils.getBitcoinEntityTest
import com.anamarin.bitcoinpricesapp.data.repositories.CHART_NAME
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.anamarin.bitcoinpricesapp.domain.usescases.GetBitcoinInfoUsecase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito


class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var useCaseTest: GetBitcoinInfoUsecase

    private lateinit var networkStatus: NetworkStatus

    private val quantity = 2

    private val period: String = WEEK_PERIOD

    private val name: String = CHART_NAME

    @get:Rule
    val schedulerRule = RxSchedulerRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        useCaseTest = mock()
        networkStatus = mock()
        mainViewModel = MainViewModel(useCaseTest, networkStatus)
    }

    @Test
    fun viewModelDisplayDataValue_whenUseCaseCallReturnSuccess() {
        val bitcoinInfoEntity = getBitcoinEntityTest()
        val singleBitcoinInfoEntity = Single.just(Success(bitcoinInfoEntity))

        Mockito.doReturn(singleBitcoinInfoEntity).`when`(useCaseTest).callSingle(quantity, period, name)

        val testObserver: TestObserver<Outcome<BitcoinInfoEntity>> = mainViewModel.getBitcoinInfo(quantity, period, name).test()

        testObserver.assertValues(Success(bitcoinInfoEntity))
        testObserver.dispose()
    }

    @Test
    fun viewModelDisplaySomeError_whenUseCaseCallReturnFailure() {
        val e = Failure(Exception())
        val mockLocalFailure = Single.just(e)

        Mockito.doReturn(mockLocalFailure).`when`(useCaseTest).callSingle(quantity, period, name)

        val testObserver: TestObserver<Outcome<BitcoinInfoEntity>> = mainViewModel.getBitcoinInfo(quantity, period, name).test()

        testObserver.assertValue { it is Failure }

        testObserver.dispose()
    }

    @Test
    fun viewModelDisplaySomeError_whenUseCaseCallThrowsError() {
        val e = Exception()
        val mockLocalFailure = Single.error<Exception>(e)

        Mockito.doReturn(mockLocalFailure).`when`(useCaseTest).callSingle(quantity, period, name)

        val testObserver: TestObserver<Outcome<BitcoinInfoEntity>> = mainViewModel.getBitcoinInfo(quantity, period, name).test()

        testObserver.assertError { it is Exception }

        testObserver.dispose()
    }

    @Test
    fun viewModelShowEmitNetworkStatus() {
        val hasConnection = true

        whenever(networkStatus.hasNetworkAccess()).thenReturn(hasConnection)

        mainViewModel.checkNetWorkStatus()

        assertEquals(mainViewModel.liveDataConnection.value, hasConnection)
    }
}