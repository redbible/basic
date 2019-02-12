package coinone.co.kr.official.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import mobile.doremit.BuildConfig
import java.util.concurrent.TimeUnit

object OkHttpClient {

    private const val CONNECT_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L

    fun createOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            //                if (!BuildConfig.DEBUG) {
//                    val cf = CertificateFactory.getInstance("X.509")
//                    val caInput = BufferedInputStream(application.assets.open("wildcard3.coinone.co.kr.crt"))
//                    val ca = cf.generateCertificate(caInput)
//                    caInput.close()
//
//                    // Create a KeyStore containing our trusted CAs
//                    val keyStoreType = KeyStore.getDefaultType()
//                    val keyStore = KeyStore.getInstance(keyStoreType)
//                    keyStore.load(null, null)
//                    keyStore.setCertificateEntry("ca", ca)
//
//                    // Create a TrustManager that trusts the CAs in our KeyStore
//                    val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
//                    val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
//                    tmf.init(keyStore)
//
//                    // Create an SSLContext that uses our TrustManager
//                    val sslContext = SSLContext.getInstance("TLS")
//                    sslContext.init(null, tmf.trustManagers, null)
//                    sslSocketFactory(sslContext.socketFactory).hostnameVerifier { _, _ -> true }
//                }

            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
        }.addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }).build()
}