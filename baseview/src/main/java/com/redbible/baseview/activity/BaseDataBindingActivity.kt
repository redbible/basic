package com.redbible.baseview.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrInterface
import com.r0adkll.slidr.model.SlidrPosition
import com.redbible.baseview.Disposer
import com.redbible.baseview.R
import com.redbible.baseview.disposeOnPause
import com.redbible.baseview.fragment.BaseDataBindingBottomSheetFragment
import com.redbible.baseview.fragment.BaseDataBindingDialogFragment
import com.redbible.baseview.fragment.BaseDataBindingFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

abstract class BaseDataBindingActivity<B : ViewDataBinding>(
    private val layoutId: Int,
    private val isPopup: Boolean = false
) :
    AppCompatActivity(), Disposer {

    companion object {
        private var showToast = false
        private var enabledBackPressedToast = true
        private var enabledSliderFinish = true
        private var enabledPortrait = true

        @SuppressLint("CheckResult")
        fun showToast(isTaskRoot: Boolean): Boolean {
            val ret = isTaskRoot && !showToast

            if (ret) {
                showToast = true
                Observable.just(1)
                    .delay(2, TimeUnit.SECONDS)
                    .subscribe { showToast = false }
            }

            return ret
        }

        /**
         * it make disabled Toast Message when onBackPressed in rootActivity once
         */
        fun disabledBackPressedToast() {
            enabledBackPressedToast = false
        }

        /**
         * it make enabled Slidr finish activity. recommend call in rootActivity once
         */
        fun enabledSliderFinish(enable: Boolean) {
            enabledSliderFinish = enable
        }

        /**
         * it make disabled forced portrait activity. recommend call in rootActivity once
         */
        fun disabledPortrait() {
            enabledPortrait = false
        }
    }

    private val compositeDisposableOnPause = CompositeDisposable()
    private val compositeDisposableOnDestroy = CompositeDisposable()
    private var slidrInterface: SlidrInterface? = null

    protected lateinit var binding: B
    abstract fun B.onBind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBasicSetting()
        setupProperties(intent?.extras)
        setContentView(layoutId)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.onBind()
    }

    protected open fun setupProperties(bundle: Bundle?) {
        //setup properties
        //bundle?.getString(KEY)
    }

    private fun initBasicSetting() {
        if (enabledPortrait && Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {       //O의 경우 orientation 설정하면 강제종료됨
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        if (enabledSliderFinish && !isTaskRoot) {
            Slidr.attach(this)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
                if (!isTaskRoot && !isPopup) {
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
        }
    }

    /**
     * show software keyboard for view
     * @param view target for keyboard typing, ex) EditText
     */
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    public fun showKeyboard(view: View) {
        Observable.just(1)
            .delay(300, TimeUnit.MILLISECONDS)  //activity created time
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.run {
                    requestFocus()
                    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
                        view,
                        0
                    )
                }
            }.disposeOnPause(this)
    }

    /**
     * hide software keyboard for view
     * @param view target for keyboard,
     */
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    public fun hideKeyboard(view: View? = currentFocus) {
        view?.run {
            post {
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    windowToken,
                    0
                )
            }
        }
    }

    fun getSlidr(@IdRes rootView: Int): SlidrInterface {
        if (slidrInterface == null) {
            slidrInterface = Slidr.replace(findViewById(rootView), SlidrConfig.Builder().position(SlidrPosition.LEFT).build())
        }

        return slidrInterface!!
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.fragments.filter { it.isVisible }.forEach {
            when (it) {
                is BaseDataBindingFragment<*> -> it.runOnResume()
                is BaseDataBindingDialogFragment<*> -> it.runOnResume()
                is BaseDataBindingBottomSheetFragment<*> -> it.runOnResume()
                else -> {
                }
            }
        }
    }

    override fun onPause() {
        compositeDisposableOnPause.clear()

        supportFragmentManager.fragments.forEach {
            when (it) {
                is BaseDataBindingFragment<*> -> it.runOnPause()
                is BaseDataBindingDialogFragment<*> -> it.runOnPause()
                is BaseDataBindingBottomSheetFragment<*> -> it.runOnPause()
                else -> {
                }
            }
        }
        super.onPause()
    }

    override fun onDestroy() {
        compositeDisposableOnDestroy.clear()
        super.onDestroy()
    }

    override fun disposeOnPause(disposable: Disposable) {
        compositeDisposableOnPause.add(disposable)
    }

    override fun disposeOnDestroy(disposable: Disposable) {
        compositeDisposableOnDestroy.add(disposable)
    }

    override fun onBackPressed() {
        if (enabledBackPressedToast && showToast(isTaskRoot)) {
            Toast.makeText(this, getString(R.string.backPressedToast), Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
            if (enabledSliderFinish && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
                if (!isTaskRoot && !isPopup) {
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                }
            }
        }
    }
}