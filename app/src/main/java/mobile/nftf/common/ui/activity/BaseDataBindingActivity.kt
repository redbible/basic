package coinone.co.kr.official.common.ui.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDataBindingActivity<B : ViewDataBinding>(private val layoutId: Int) : BaseActivity(layoutId) {

    protected lateinit var binding: B

    abstract fun B.onBind()

    override fun setContentView() {
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun setupView() {
        binding.run {
            lifecycleOwner = this@BaseDataBindingActivity
            onBind()
        }
    }
}