package mobile.nftf.di

import coinone.co.kr.official.network.OkHttpClient
import mobile.nftf.BuildConfig
import mobile.nftf.network.ApiCallAdapterFactory
import mobile.nftf.network.api.ApiTest
import mobile.nftf.util.GsonFactory
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
