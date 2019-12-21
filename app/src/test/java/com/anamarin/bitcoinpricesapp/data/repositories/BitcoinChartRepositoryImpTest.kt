package com.anamarin.bitcoinpricesapp.data.repositories

import com.anamarin.bitcoinpricesapp.core.error.ServerException
import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatus
import com.anamarin.bitcoinpricesapp.core.result.Failure
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.core.utils.getBitcoinInfoDTOTest
import com.anamarin.bitcoinpricesapp.core.utils.getBitcoinInfoModelTest
import com.anamarin.bitcoinpricesapp.core.utils.getListBitcoinCoordinatesModelTest
import com.anamarin.bitcoinpricesapp.data.api.BitcoinInfoClient
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoLocal
import com.anamarin.bitcoinpricesapp.data.models.BitcoinChart
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


const val CHART_NAME = "market-place"

class BitcoinInfoRepositoryImpTest {

    private lateinit var repository: BitcoinInfoRepositoryImp

    private lateinit var mockLocalData: BitcoinInfoLocal
    private lateinit var mockRemoteData: BitcoinInfoClient
    private lateinit var mockNetworkStatus: NetworkStatus

    private val quantity = 2
    private val period: String = WEEK_PERIOD
    private val name: String = CHART_NAME
    val timestamp = quantity.toString() + period

    @Before
    fun setUp() {
        mockLocalData = mock()
        mockRemoteData = mock()
        mockNetworkStatus = mock()

        repository = BitcoinInfoRepositoryImp(mockLocalData, mockRemoteData, mockNetworkStatus)
    }

    // Online behavior
    @Test
    fun getRemoteDataSuccessfully() {
        val dto = getBitcoinInfoDTOTest()
        val bitcoinChart = BitcoinChart(dto, dto.values)

        val singleBitcoin = Single.just(dto)

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        Mockito.doReturn(singleBitcoin).`when`(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)

        val testObserver: TestObserver<Outcome<BitcoinChart>> = repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)

        testObserver.assertValues(Success(bitcoinChart))

        testObserver.dispose()
    }


    @Test
    fun saveTheDataLocally_whenCallToRemoteDataSourceIsSuccessfully() {
        val dto = getBitcoinInfoDTOTest()
        val bitcoinChart = BitcoinChart(dto, dto.values)

        val singleBitcoin = Single.just(dto)

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)

        Mockito.doReturn(singleBitcoin).`when`(mockRemoteData)
            .getBitcoinInfoInPeriodSingle(name, timestamp)

        val testObserver: TestObserver<Outcome<BitcoinChart>> = repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)
        verify(mockLocalData).saveBitcoinInfo(bitcoinChart)

        testObserver.dispose()
    }

    @Test
    fun useLocalData_whenCallToRemoteDataSourceIsUnsuccessfully() {
        val bitcoinChart = BitcoinChart(getBitcoinInfoModelTest(), getListBitcoinCoordinatesModelTest())
        val mockRemoteFailure = Single.error<Exception>(ServerException())

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        Mockito.doReturn(mockRemoteFailure).`when`(mockRemoteData)
            .getBitcoinInfoInPeriodSingle(name, timestamp)
        Mockito.doReturn(bitcoinChart).`when`(mockLocalData).getLastBitcoinInfoSaved(timestamp)

        val testObserver: TestObserver<Outcome<BitcoinChart>> =
            repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)
        verify(mockLocalData).getLastBitcoinInfoSaved(timestamp)
        verifyNoMoreInteractions(mockRemoteData)
        verifyNoMoreInteractions(mockLocalData)

        testObserver.assertValues(Success(bitcoinChart))

        testObserver.dispose()
    }

    @Test
    fun returnFailure_whenCallToRemoteDataSourceIsUnsuccessfully_andLocalDataIsEmptyRx() {
        val mockRemoteFailure = Single.error<Exception>(ServerException())

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)
        Mockito.doReturn(mockRemoteFailure).`when`(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)
        Mockito.doReturn(null).`when`(mockLocalData).getLastBitcoinInfoSaved(timestamp)

        val testObserver: TestObserver<Outcome<BitcoinChart>> =
            repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockRemoteData).getBitcoinInfoInPeriodSingle(name, timestamp)
        verify(mockLocalData).getLastBitcoinInfoSaved(timestamp)

        testObserver.assertValue { it is Failure }

        testObserver.dispose()
    }


    //Offline behavior

    @Test
    fun returnDataFromLocalSource() {
        val bitcoinChart = BitcoinChart(getBitcoinInfoModelTest(), getListBitcoinCoordinatesModelTest())

        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(false)
        Mockito.doReturn(bitcoinChart).`when`(mockLocalData).getLastBitcoinInfoSaved(timestamp)

        val testObserver: TestObserver<Outcome<BitcoinChart>> =
            repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockLocalData).getLastBitcoinInfoSaved(timestamp)
        verifyZeroInteractions(mockRemoteData)

        testObserver.assertValues(Success(bitcoinChart))

        testObserver.dispose()
    }

    @Test
    fun returnFailureWhenLocalSourceIsEmpty() {
        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(false)
        Mockito.doReturn(null).`when`(mockLocalData).getLastBitcoinInfoSaved(timestamp)

        val testObserver: TestObserver<Outcome<BitcoinChart>> =
            repository.fetchBitcoinInfoSingle(quantity, period, name).test()

        verify(mockNetworkStatus).hasNetworkAccess()
        verify(mockLocalData).getLastBitcoinInfoSaved(timestamp)
        verifyZeroInteractions(mockRemoteData)

        testObserver.assertValue { it is Failure }

        testObserver.dispose()
    }


}