package mobile.doremit.di

import coinone.co.kr.official.network.OkHttpClient
import org.koin.dsl.module.module
import mobile.doremit.BuildConfig
import mobile.doremit.network.ApiCallAdapterFactory
import mobile.doremit.network.api.ApiTest
import mobile.doremit.util.GsonFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val NETWORK_OK_HTTP_CLIENT = "okHttpClient"
const val NETWORK_RETROFIT_ADAPTER = "retrofitAdapter"

val networkModule = module {
    single(name = NETWORK_OK_HTTP_CLIENT) {
        OkHttpClient.createOkHttpClient()
    }
    single(name = NETWORK_RETROFIT_ADAPTER) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(get(NETWORK_OK_HTTP_CLIENT))
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.create()))
            .addCallAdapterFactory(ApiCallAdapterFactory())
            .build()
    }
    single { (get(NETWORK_RETROFIT_ADAPTER) as Retrofit).create(ApiTest::class.java) }
}
