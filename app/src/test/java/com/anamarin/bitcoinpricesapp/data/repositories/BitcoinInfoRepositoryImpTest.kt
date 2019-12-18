package com.anamarin.bitcoinpricesapp.data.repositories

import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatus
import com.anamarin.bitcoinpricesapp.core.result.Outcome
import com.anamarin.bitcoinpricesapp.core.result.Outcome.Success
import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
import com.anamarin.bitcoinpricesapp.core.utils.getTestBitcoinInfoModel
import com.anamarin.bitcoinpricesapp.data.api.BitcoinInfoClient
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoDao
import org.junit.Before
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class BitcoinInfoRepositoryImpTest {

    private lateinit var repository: BitcoinInfoRepositoryImp

    private lateinit var mockLocalData: BitcoinInfoDao
    private lateinit var mockRemoteData: BitcoinInfoClient
    private lateinit var mockNetworkStatus: NetworkStatus

    @Before
    fun setUp(){
        mockLocalData = mock()
        mockRemoteData = mock()
        mockNetworkStatus = mock()

        repository = BitcoinInfoRepositoryImp(mockLocalData, mockRemoteData, mockNetworkStatus)
    }

    @Test
    fun isCheckingIfTheDeviceHasInternet(){
        whenever(mockNetworkStatus.hasNetworkAccess()).thenReturn(true)

        repository.fetchBitcoinInfo(1, WEEK_PERIOD)

        verify(mockNetworkStatus).hasNetworkAccess()
    }


}