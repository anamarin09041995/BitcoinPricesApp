package com.anamarin.bitcoinpricesapp.data.repositories

import com.anamarin.bitcoinpricesapp.core.error.ServerException
import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatus
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.core.utils.getTestBitcoinInfoModel
import com.anamarin.bitcoinpricesapp.core.utils.schedulers.TrampolineSchedulerProvider
import com.anamarin.bitcoinpricesapp.data.api.BitcoinInfoClient
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoDao
import com.anamarin.bitcoinpricesapp.data.models.BitcoinInfoModel
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


const val CHART_NAME = "market-place"

class BitcoinInfoRepositoryImpTest {

    private lateinit var repository: BitcoinInfoRepositoryImp

    private lateinit var mockLocalData: BitcoinInfoDao
    private lateinit var mockRemoteData: BitcoinInfoClient
    private lateinit var mockNetworkStatus: NetworkStatus

    private val quantity = 2
    private val period: String = WEEK_PERIOD
    private val name: String = CHART_NAME
    private var schedulerProvider = TrampolineSchedulerProvider()


    private val model = getTestBitcoinInfoModel()

    @Before
    fun setUp() {
        mockLocalData = mock()
        mockRemoteData = mock()
        mockNetworkStatus = mock()

        repository = BitcoinInfoRepositoryImp(mockLocalData, mockRemoteData, mockNetworkStatus)
    }

    @Test
    fun isCheckingIfTheDeviceHasInternet() {
        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)

        repository.fetchBitcoinInfo(quantity, period)

        verify(mockNetworkStatus).hasNetworkAccess()
    }

    // Online behavior

    @Test
    fun isGettingRemoteDataSuccessfully() {
        val timestamp = quantity.toString() + period

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        whenever(mockRemoteData.getBitcoinInfoInPeriod(timestamp)).thenReturn(Success(model))

        val result = repository.fetchBitcoinInfo(quantity, period)

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriod(timestamp)

        assert(result is Success)
        assertEquals((result as Success).data, model)
    }

    //Rx
    @Test
    fun isGettingRemoteDataSuccessfullyRx() {
        val timestamp = quantity.toString() + period

        val singleBitcoinInfo = Single.just(model)

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        Mockito.doReturn(singleBitcoinInfo).`when`(mockRemoteData)
            .getBitcoinInfoInPeriodSingle(name, timestamp)

        val testObserver: TestObserver<Outcome<BitcoinInfoModel>> =
            repository.fetchBitcoinInfoSingle(quantity, period, name)
                .test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)

        testObserver.assertValues(Success(model))

        testObserver.dispose()
    }

    @Test
    fun isSavingTheDataLocally_whenCallToRemoteDataSourceIsSuccessfully() {
        val timestamp = quantity.toString() + period

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        whenever(mockRemoteData.getBitcoinInfoInPeriod(timestamp)).thenReturn(Success(model))

        val result = repository.fetchBitcoinInfo(quantity, period)

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriod(timestamp)
        verify(mockLocalData).saveBitcoinInfo(model)

        assert(result is Success)
        assertEquals((result as Success).data, model)
    }


    @Test
    fun isUsingLocalData_whenCallToRemoteDataSourceIsUnsuccessfully() {
        val timestamp = quantity.toString() + period

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        whenever(mockRemoteData.getBitcoinInfoInPeriod(timestamp)).thenAnswer {
            Failure(
                ServerException()
            )
        }
        whenever(mockLocalData.getLastBitcoinInfoSaved()).thenReturn(model)

        val result = repository.fetchBitcoinInfo(quantity, period)

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriod(timestamp)
        verify(mockLocalData).getLastBitcoinInfoSaved()
        verifyNoMoreInteractions(mockRemoteData)

        assert(result is Success<BitcoinInfoModel>)
        assertEquals((result as Success).data, model)
    }



    @Test
    fun isReturningFailure_whenCallToRemoteDataSourceIsUnsuccessfully_andLocalDataIsEmpty() {
        val timestamp = quantity.toString() + period

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        whenever(mockRemoteData.getBitcoinInfoInPeriod(timestamp)).thenAnswer {
            Failure(
                ServerException()
            )
        }
        whenever(mockLocalData.getLastBitcoinInfoSaved()).thenReturn(null)

        val result = repository.fetchBitcoinInfo(quantity, period)

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriod(timestamp)
        verify(mockLocalData).getLastBitcoinInfoSaved()

        assert(result is Failure)
    }


    //Offline behavior

    @Test
    fun isReturningDataFromLocalSource() {
        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(false)
        whenever(mockLocalData.getLastBitcoinInfoSaved()).thenReturn(model)

        val result = repository.fetchBitcoinInfo(quantity, period)

        verifyZeroInteractions(mockRemoteData)
        verify(mockNetworkStatus).hasNetworkAccess()
        assertEquals((result as Success).data, model)
    }


}