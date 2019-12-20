package com.anamarin.bitcoinpricesapp.data.api

import com.anamarin.bitcoinpricesapp.core.utils.getBitcoinInfoDTOTest
import com.anamarin.bitcoinpricesapp.data.repositories.CHART_NAME
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class BitcoinInfoClientTest{

    private lateinit var bitcoinInfoClientImp:  BitcoinInfoClientImp

    private lateinit var mockRetrofit: BitcoinRetrofitClient

    private val name = CHART_NAME
    private val timestamp = "1weeks"

    @Before
    fun setUp(){
        mockRetrofit = mock()
        bitcoinInfoClientImp = BitcoinInfoClientImp(mockRetrofit)
    }

    @Test
    fun returnBitcoinInfo_WhenServiceResponseSuccessfully(){
        val bitcoinDTO: BitcoinChartDTO = getBitcoinInfoDTOTest()
        val singleBitcoinInfoDTO = Single.just(bitcoinDTO)

        Mockito.doReturn(singleBitcoinInfoDTO).`when`(mockRetrofit).getBitcoinInfoForPeriod(name, timestamp)

        val testObserver: TestObserver<BitcoinChartDTO> =
            bitcoinInfoClientImp.getBitcoinInfoInPeriodSingle(name, timestamp).test()

        testObserver.assertValues(bitcoinDTO)
    }

    @Test
    fun returnError_WhenServiceResponseIsUnsuccessfull(){
        val exception = Exception()
        val remoteException = Single.error<Exception>(exception)

        Mockito.doReturn(remoteException).`when`(mockRetrofit).getBitcoinInfoForPeriod(name, timestamp)

        val testObserver: TestObserver<BitcoinChartDTO> =
            bitcoinInfoClientImp.getBitcoinInfoInPeriodSingle(name, timestamp).test()

        testObserver.assertError(exception)
    }
}