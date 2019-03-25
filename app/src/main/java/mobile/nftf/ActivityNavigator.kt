package mobile.nftf

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import mobile.nftf.ui.MainActivity
import mobile.nftf.ui.SplashActivity
import java.util.*

/**
 * UI component 이외의 곳 (ViewModel, Service, 등) 에서 사용하지 않도록 주의할 것.
 * context 에 대한 reference 가 풀리지 않아 memory leak 이 발생할 수 있음.
 */
class ActivityNavigator private constructor(private val context: Context) {

    companion object {
        const val KEY_DATA = "data"

        @JvmStatic
        fun with(context: Context): ActivityNavigator = ActivityNavigator(context)

        @JvmStatic
        fun with(fragment: Fragment): ActivityNavigator = with(
            fragment.context
                ?: fragment.activity!!
        )
    }

    val stack: ArrayList<Intent> = ArrayList()

    fun start(uriString: String) {
//        val uri = Uri.parse(uriString)
//        when (uri.host) {
//            "home" -> main(MainActivity.PAGE_HOME, uri.getQueryParameter("tab").toUpperCase()).start()
//            "trading" -> {
//                val tradingPair = uri.getQueryParameter("tradingpair").toUpperCase()
//
//                main(MainActivity.PAGE_HOME, tradingPair.split("-")[1])
//                        .add()
//                        .trade(arrayListOf(Ticker(tradingPair)), 0)
//                        .start()
//
//                FirebaseEventLogging.put(EventLogging.EventTradingEnter, From.UrlScheme)
//            }
//            "coinlog" -> {
//                val symbol = uri.getQueryParameter("coin")
//                val marketRepository = CoinoneApplication.globalApplicationContext.get<MarketRepository>()
//                val coin = marketRepository.getCoin(symbol.toUpperCase())
//                if (coin != null) {
//                    main(MainActivity.PAGE_ASSET)
//                            .add()
//                            .assetDetail(Wallet(coin.symbol, name = coin.name, balance = 0.0))
//                            .start()
//
//                    FirebaseEventLogging.put(EventLogging.EventBalanceDetail, Bundle().apply {
//                        putString(Value.From.name, From.UrlScheme.name)
//                        putString(Value.Coin.name, coin.symbol)
//                    })
//                }
//            }
//            "webview" -> {
//                main().add()
//                        .webview(URLDecoder.decode(uri.getQueryParameter("url"), "UTF-8"))
//                        .start()
//            }
//        }
    }

    fun splash() =
        MyIntent(SplashActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

    fun main() =
        MyIntent(MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        }

    inner class MyIntent : Intent {

        constructor(cls: Class<*>?) : super(context, cls)

        constructor(action: String?, uri: Uri?) : super(action, uri)

        fun add(): ActivityNavigator {
            stack.add(this)
            return this@ActivityNavigator
        }

        fun start() {
            stack.add(this)
            context.startActivities(stack.toTypedArray())
        }

        fun startForResult(requestCode: Int) {
            if (context !is Activity) {
                throw IllegalArgumentException("startActivityForResult 는 context 가 activity 일 경우에만 사용할 수 있습니다.")
            }

            context.startActivityForResult(this, requestCode)
        }
    }
}