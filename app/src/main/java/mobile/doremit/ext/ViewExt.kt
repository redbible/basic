package mobile.doremit.ext

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("app:selected")
fun View.setSelected(input: Boolean) {
    isSelected = input
}