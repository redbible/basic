package com.redbible.baseview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.redbible.baseview.Disposer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDataBindingFragment<B : ViewDataBinding>(private val layoutId: Int) : Fragment(),
    Disposer {

    val hasFocusObservers = mutableListOf<(Boolean) -> Unit>()
    val lifecycleObservers = mutableListOf<(Lifecycle.Event) -> Unit>()

    private val compositeDisposableOnPause = CompositeDisposable()
    private val compositeDisposableOnDestroy = CompositeDisposable()

    protected lateinit var binding: B

    abstract fun B.onBind()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupProperties(arguments)

        return createView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.onBind()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runOnResume()
    }

    override fun onDestroy() {
        runOnPause()
        compositeDisposableOnDestroy.clear()
        super.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (hidden) {
            runOnPause()
        } else {
            runOnResume()
        }
    }

    open fun runOnResume() {

    }

    open fun runOnPause() {
        compositeDisposableOnPause.clear()
    }

    override fun disposeOnPause(disposable: Disposable) {
        compositeDisposableOnPause.add(disposable)
    }

    override fun disposeOnDestroy(disposable: Disposable) {
        compositeDisposableOnDestroy.add(disposable)
    }

    protected open fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    protected open fun setupProperties(bundle: Bundle?) {
        //setup properties
        //bundle?.getString(KEY)
    }

    open fun onBackPressed(): Boolean = true

    fun commitAllowingStateLoss(fragmentManager: FragmentManager?, @IdRes containerViewId: Int) {
        fragmentManager?.run {
            beginTransaction().replace(containerViewId, this@BaseDataBindingFragment)
                .commitAllowingStateLoss()
        }
    }
}