package com.example.basic.di

import com.example.basic.BuildConfig
import com.example.basic.network.adapter.ApiCallAdapterFactory
import com.example.basic.network.adapter.OkHttpClient
import com.example.basic.network.api.ApiTest
import com.example.basic.util.GsonFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.createOkHttpClient()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.create()))
            .addCallAdapterFactory(ApiCallAdapterFactory())
            .build()
    }
    single { (get() as Retrofit).create(ApiTest::class.java) }
}
