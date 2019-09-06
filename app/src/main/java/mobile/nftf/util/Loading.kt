package mobile.nftf.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import mobile.nftf.R

object Loading {
    private var dialog: Dialog? = null

    fun show(context: Context?) {
        dissmiss()

        if (context != null) {
            dialog = Dialog(context, R.style.CustomDialogTheme)
                .apply {
                    requestWindowFeature(Window.FEATURE_NO_TITLE)
                    setContentView(R.layout.custom_dialog)
                    window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    setCanceledOnTouchOutside(true)
                    setCancelable(true)
                    show()
                }
        }
    }

    fun dissmiss() {
        if (dialog != null) {
            Observable.just(dialog)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it!!.dismiss() }
        }
    }
}