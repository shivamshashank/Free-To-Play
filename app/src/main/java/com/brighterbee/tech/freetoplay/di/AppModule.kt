package com.brighterbee.tech.freetoplay.di

import com.brighterbee.tech.freetoplay.common.Constants
import com.brighterbee.tech.freetoplay.common.Constants.CONNECTION_TIMEOUT
import com.brighterbee.tech.freetoplay.common.Constants.READ_TIMEOUT
import com.brighterbee.tech.freetoplay.common.Constants.WRITE_TIMEOUT
import com.brighterbee.tech.freetoplay.data.remote.FreeToGameApi
import com.brighterbee.tech.freetoplay.data.repository.GameRepositoryImpl
import com.brighterbee.tech.freetoplay.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().also {
                        it
                            .addHeader("X-Requested-With", "XMLHttpRequest")
                            .addHeader("Content-Type", "application/json")
                    }.build()
                )
            }
    }.build()

    @Provides
    @Singleton
    fun provideFreeToGameApi(): FreeToGameApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FreeToGameApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGameRepository(freeToGameApi: FreeToGameApi): GameRepository{
        return GameRepositoryImpl(freeToGameApi)
    }
}