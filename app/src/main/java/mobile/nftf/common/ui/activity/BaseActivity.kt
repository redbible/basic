package coinone.co.kr.official.common.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity(private val layoutId: Int) : AppCompatActivity() {

    protected abstract fun setupView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupProperties(intent?.extras)
        setContentView()
        setupView()
    }

    protected open fun setupProperties(bundle: Bundle?) {
        //do nothing
    }

    protected open fun setContentView() {
        setContentView(layoutId)
    }
}