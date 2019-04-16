package coinone.co.kr.official.common.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import mobile.nftf.util.Disposer

abstract class BaseActivity(private val layoutId: Int) : AppCompatActivity(), Disposer {

    protected val compositeDisposableOnPause = CompositeDisposable()
    protected val compositeDisposableOnStop = CompositeDisposable()
    protected val compositeDisposableOnDestroy = CompositeDisposable()

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

    override fun onStop() {
        compositeDisposableOnStop.clear()
        super.onStop()
    }

    override fun onDestroy() {
        compositeDisposableOnDestroy.clear()
        super.onDestroy()
    }

    override fun disposeOnPause(disposable: Disposable) {
        compositeDisposableOnPause.add(disposable)
    }

    override fun disposeOnStop(disposable: Disposable) {
        compositeDisposableOnStop.add(disposable)
    }

    override fun disposeOnDestroy(disposable: Disposable) {
        compositeDisposableOnDestroy.add(disposable)
    }
}