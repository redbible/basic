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
        if (dialog?.isShowing == true) {
            return
        }

        dissmiss()

        if (context != null) {
            Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dialog = Dialog(context, R.style.CustomDialogTheme)
                        .apply {
                            requestWindowFeature(Window.FEATURE_NO_TITLE)
                            setContentView(R.layout.loading_dialog)
                            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            setCanceledOnTouchOutside(true)
                            setCancelable(true)
                            show()
                        }
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