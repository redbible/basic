package redbible.basic.network

import coinone.co.kr.official.common.network.api.adapter.RxApiResponseCallAdapter
import coinone.co.kr.official.common.network.api.adapter.RxApiResponseCallAdapterFactory
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class CoinoneApiCallAdapterFactory : RxApiResponseCallAdapterFactory() {

    override fun createAdapter(rxAdapter: CallAdapter<*, *>?, returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): RxApiResponseCallAdapter<*>? {
        if (rxAdapter == null) {
            return null
        }

        return CoinoneApiCallAdapter(rxAdapter)
    }
}