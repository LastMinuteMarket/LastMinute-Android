package com.lastminute.repository

import androidx.compose.runtime.key
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lastminute.repository.api.PlacementApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverService {
    const val BASE_URL = "https://openapi.naver.com/v1/search/"

    private val debugInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val keyInterceptor = Interceptor { chain ->
        val headers = chain.request().headers.newBuilder()
            .add("X-Naver-Client-id", "7tzhLpd1A7KHPeU46h71")
            .add("X-Naver-Client-Secret", "ZHezN8Hs0G")
            .build()
        val request = chain.request()
            .newBuilder()
            .headers(headers)
            .build()
        chain.proceed(request)
    }

    private val apiClient = OkHttpClient().newBuilder()
        .addInterceptor(debugInterceptor)
        .addInterceptor(keyInterceptor)
        .build()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val placementApi: PlacementApi = retrofit().create(PlacementApi::class.java)
}