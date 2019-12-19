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

    /*@Test
    fun checkIfTheDeviceHasInternet() {
        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)

        repository.fetchBitcoinInfoSingle(quantity, period, name)

        verify(mockNetworkStatus).hasNetworkAccess()
    }*/

    // Online behavior

    @Test
    fun getRemoteDataSuccessfully() {
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
    fun saveTheDataLocally_whenCallToRemoteDataSourceIsSuccessfully() {
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
        verify(mockLocalData).saveBitcoinInfo(model)

        testObserver.assertValues(Success(model))

        testObserver.dispose()

    }

    @Test
    fun useLocalData_whenCallToRemoteDataSourceIsUnsuccessfully() {
        val timestamp = quantity.toString() + period

        val mockRemoteFailure = Single.error<Exception>(ServerException())

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        Mockito.doReturn(mockRemoteFailure).`when`(mockRemoteData)
            .getBitcoinInfoInPeriodSingle(name, timestamp)
        Mockito.doReturn(model).`when`(mockLocalData).getLastBitcoinInfoSaved()

        val testObserver: TestObserver<Outcome<BitcoinInfoModel>> =
            repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)
        verify(mockLocalData).getLastBitcoinInfoSaved()
        verifyNoMoreInteractions(mockRemoteData)
        verifyNoMoreInteractions(mockLocalData)

        testObserver.assertValues(Success(model))

        testObserver.dispose()
    }

    @Test
    fun returnFailure_whenCallToRemoteDataSourceIsUnsuccessfully_andLocalDataIsEmptyRx() {
        val timestamp = quantity.toString() + period

        val mockRemoteFailure = Single.error<Exception>(ServerException())

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        Mockito.doReturn(mockRemoteFailure).`when`(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)
        Mockito.doReturn(null).`when`(mockLocalData).getLastBitcoinInfoSaved()

        val testObserver: TestObserver<Outcome<BitcoinInfoModel>> = repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)
        verify(mockLocalData).getLastBitcoinInfoSaved()

        testObserver.assertValue { it is Failure }

        testObserver.dispose()
    }


    //Offline behavior

    @Test
    fun returnDataFromLocalSource(){
        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(false)
        Mockito.doReturn(model).`when`(mockLocalData).getLastBitcoinInfoSaved()

        val testObserver: TestObserver<Outcome<BitcoinInfoModel>> = repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockLocalData).getLastBitcoinInfoSaved()
        verifyZeroInteractions(mockRemoteData)

        testObserver.assertValues(Success(model))

        testObserver.dispose()
    }

    @Test
    fun returnFailureWhenLocalSourceIsEmpty() {
        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(false)
        Mockito.doReturn(null).`when`(mockLocalData).getLastBitcoinInfoSaved()

        val testObserver: TestObserver<Outcome<BitcoinInfoModel>> = repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockLocalData).getLastBitcoinInfoSaved()
        verifyZeroInteractions(mockRemoteData)

        testObserver.assertValue { it is Failure }

        testObserver.dispose()
    }


}