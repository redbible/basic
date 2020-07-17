package mobile.nftf.ext

import android.widget.Toast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import mobile.nftf.MyApplication

fun String.showLongToastSafe() {
    Single.just(this)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { it -> Toast.makeText(MyApplication.globalApplicationContext, this, Toast.LENGTH_LONG).show() }
}