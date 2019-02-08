package coinone.co.kr.official.network

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.view.View
import android.view.WindowManager
import coinone.co.kr.official.common.network.api.model.ApiException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class RxNetworkErrorHandler {
    private var alert: AlertDialog? = null
    private var isCheckingServerStatus = false

    fun processErrorHandler(it: Throwable?) {
//        if (it != null) {
//            DLog.d("$it")
//            Loading.dissmiss()
//
//            if (!isNetworkAvailable()) {
//                toastNetWork(getString(R.string.network_error_title), getString(R.string.network_error_content))
//            } else if (isNoResponseServer(it)) {
//                toastNetWork(getString(R.string.network_delay_title), getString(R.string.network_delay_content))
//                checkServerStatus()
//            } else {
//                checkExcpiton(it)
//            }
//        }
    }
}