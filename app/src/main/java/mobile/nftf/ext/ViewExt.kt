package mobile.nftf.ext

import androidx.databinding.BindingAdapter
import android.view.View

@BindingAdapter("app:selected")
fun View.setSelected(input: Boolean) {
    isSelected = input
}