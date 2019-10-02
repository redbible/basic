package mobile.nftf.ui

import android.app.Dialog
import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.popup_basic.*
import mobile.nftf.MyApplication.Companion.getString
import mobile.nftf.R

class DialogBasic(
    context: Context,
    title: String,
    content: String
) : Dialog(context) {
    private var onClick: ((view: View) -> Unit)? = null

    init {
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_basic)
//        window.decorView.setBackgroundResource(android.R.color.transparent)

        txtTitle.text = title
        txtDescription.text = content

        btnConfirm.setOnClickListener {
            onClick?.invoke(it)
            dismiss()
        }
    }

    fun setCancel(text: String = getString(R.string.cancel)): DialogBasic {
        btnCancel.run {
            visibility = View.VISIBLE
            this.text = text
            setOnClickListener { dismiss() }
        }

        return this
    }

    fun setClickListenerConfirm(click: (view: View) -> Unit): DialogBasic {
        onClick = click
        return this
    }
}