package redbible.basic.di

import coinone.co.kr.official.network.OkHttpClient
import coinone.co.kr.official.network.RxNetworkErrorHandler
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import redbible.basic.BuildConfig
import redbible.basic.network.CoinoneApiCallAdapterFactory
import redbible.basic.util.GsonFactory
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
            .addCallAdapterFactory(CoinoneApiCallAdapterFactory())
            .build()
    }
//    single {
//        (get(NETWORK_RETROFIT_ADAPTER) as Retrofit).create(MarketApi::class.java)
//    }
    single { RxNetworkErrorHandler() }
}
