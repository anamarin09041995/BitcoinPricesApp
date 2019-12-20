//package com.anamarin.bitcoinpricesapp.presentation.viewmodels
//
//import com.anamarin.bitcoinpricesapp.core.result.Success
//import com.anamarin.bitcoinpricesapp.core.utils.WEEK_PERIOD
//import com.anamarin.bitcoinpricesapp.core.utils.getTestBitcoinInfoEntity
//import com.anamarin.bitcoinpricesapp.core.utils.getBitcoinInfoModelTest
//import com.anamarin.bitcoinpricesapp.data.repositories.CHART_NAME
//import com.anamarin.bitcoinpricesapp.domain.usescases.GetBitcoinInfoUsecase
//import com.nhaarman.mockitokotlin2.*
//import io.reactivex.Single
//import junit.framework.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mockito
//
//class MainViewModelTest {
//
//
//
//    private lateinit var mainViewModel: MainViewModel
//
//    private lateinit var useCaseTest: GetBitcoinInfoUsecase
//
//    private val quantity = 2
//
//    private val period: String = WEEK_PERIOD
//
//    private val name: String = CHART_NAME
//
//    @Before
//    fun setUp(){
//        useCaseTest = mock()
//        mainViewModel = MainViewModel(useCaseTest)
//    }
//
//    @Test
//    fun viewModelDisplayDataValue_whenUseCaseCallReturnSuccess(){
//        val bitcoinInfoModel = getBitcoinInfoModelTest()
//        val bitcoinInfoEntity = getTestBitcoinInfoEntity(bitcoinInfoModel)
//        val singleBitcoinInfoEntity = Single.just(Success(bitcoinInfoEntity))
//
//        Mockito.doReturn(singleBitcoinInfoEntity).`when`(useCaseTest).callSingle(quantity, period, name)
//
//        mainViewModel.getBitcoinInfo(quantity, period, name)
//
//
//        assertEquals(bitcoinInfoEntity, mainViewModel.data)
//
//    }
//
//    @Test
//    fun viewModelDisplaySomeError_whenUseCaseCallReturnError(){
////        val e = Exception()
////        val mockLocalFailure = Single.error<Exception>(e)
////
////        Mockito.doReturn(mockLocalFailure).`when`(useCaseTest).callSingle(quantity, period, name)
////
////        val testObserver: TestObserver<Outcome<BitcoinInfoEntity>> = mainViewModel.getBitcoinInfo(quantity, period, name).test()
////
////        testObserver.assertValue { it is Failure }
////
////        testObserver.dispose()
//    }
//}