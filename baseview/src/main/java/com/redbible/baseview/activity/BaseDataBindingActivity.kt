package com.redbible.baseview.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.r0adkll.slidr.Slidr
import com.redbible.baseview.Disposer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDataBindingActivity<B : ViewDataBinding>(private val layoutId: Int) : AppCompatActivity(), Disposer {

    private val compositeDisposableOnPause = CompositeDisposable()
    private val compositeDisposableOnDestroy = CompositeDisposable()

    protected lateinit var binding: B
    abstract fun B.onBind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSlideFinish()
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

    private fun initSlideFinish() {
        if (!isTaskRoot) {
            Slidr.attach(this)
        }
    }

    override fun onPause() {
        compositeDisposableOnPause.clear()
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
}