package com.example.cupboardigi.di.module

import com.example.cupboardigi.BuildConfig
import com.example.cupboardigi.data.endpoint.ApiServiceStorage
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object BasicNetworkModule {

    private fun provideCacheInterceptor() = run {
        okhttp3.Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val maxAge =
                60 // read from cache for 60 seconds even if there is internet connection
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }
    }

    private fun provideHttpLoggingInterceptor() = run {
        HttpLoggingInterceptor().apply {
            apply { level = HttpLoggingInterceptor.Level.BODY }
        }
    }

    private fun provideMoshiConverter(): Moshi = run {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    fun provideOkHttpClient() = run {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .addInterceptor(provideCacheInterceptor())
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.build()
    }



    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): ApiServiceStorage {
        return retrofit.create(ApiServiceStorage::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(provideMoshiConverter()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}