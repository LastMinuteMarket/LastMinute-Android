package com.lastminute.repository

import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lastminute.repository.api.SearchApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

object NetworkService {

    const val BASE_URL = "http://10.0.2.2:8080/"

    var userId: Int = 1

    private val debugInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val authInterceptor = Interceptor { chain ->
        val url = chain.request().url.newBuilder().addQueryParameter("userId", userId.toString()).build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }

    private val apiClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(debugInterceptor)
        .build()

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter())
        .create()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val searchApi: SearchApi = retrofit().create(SearchApi::class.java)


}