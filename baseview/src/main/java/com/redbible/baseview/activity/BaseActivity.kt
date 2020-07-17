package com.redbible.baseview.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redbible.baseview.Disposer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity(private val layoutId: Int) : AppCompatActivity(), Disposer {

    private val compositeDisposableOnPause = CompositeDisposable()
    private val compositeDisposableOnDestroy = CompositeDisposable()

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