package com.anamarin.bitcoinpricesapp.core.di.modules

import android.content.Context
import androidx.room.Room
import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatus
import com.anamarin.bitcoinpricesapp.core.networkStatus.NetworkStatusImp
import com.anamarin.bitcoinpricesapp.core.utils.BASE_URL
import com.anamarin.bitcoinpricesapp.core.utils.DATABASE_NAME
import com.anamarin.bitcoinpricesapp.data.api.BitcoinInfoClient
import com.anamarin.bitcoinpricesapp.data.api.BitcoinInfoClientImp
import com.anamarin.bitcoinpricesapp.data.api.BitcoinRetrofitClient
import com.anamarin.bitcoinpricesapp.data.local.AppDatabase
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoLocal
import com.anamarin.bitcoinpricesapp.data.local.BitcoinInfoLocalImp
import com.anamarin.bitcoinpricesapp.data.repositories.BitcoinInfoRepositoryImp
import com.anamarin.bitcoinpricesapp.domain.repositories.BitcoinInfoRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideBitcoinRetrofitClient(retrofit: Retrofit): BitcoinRetrofitClient {
        return retrofit.create(BitcoinRetrofitClient::class.java)
    }

    @Singleton
    @Provides
    fun provideBitcoinInfoClient(retrofit: BitcoinRetrofitClient): BitcoinInfoClient {
        return BitcoinInfoClientImp(retrofit)
    }

    @Provides
    fun generateDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder<AppDatabase>(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun networkStatus(context: Context): NetworkStatus {
        return NetworkStatusImp(context)
    }

    @Provides
    fun provideBitcoinInfoLocal(database: AppDatabase): BitcoinInfoLocal {
        return BitcoinInfoLocalImp(database.bitcoinInfoDao())
    }

    @Provides
    fun provideBitcoinRepository(
        retrofitClient: BitcoinInfoClient,
        local: BitcoinInfoLocal,
        networkStatus: NetworkStatus
    ): BitcoinInfoRepository {
        return BitcoinInfoRepositoryImp(local, retrofitClient, networkStatus)
    }

}