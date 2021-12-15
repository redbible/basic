package com.redbible.baseview.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.redbible.baseview.Disposer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDataBindingDialogFragment<B : ViewDataBinding>(private val layoutId: Int) : DialogFragment(), Disposer {

    abstract fun B.onBind()
    protected lateinit var binding: B

    private val compositeDisposableOnPause = CompositeDisposable()
    private val compositeDisposableOnDestroy = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupProperties(arguments)

        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
        }

        return createView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.onBind()
    }

    override fun onDestroy() {
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

    fun show(fragmentManager: FragmentManager) {
        this.show(fragmentManager, this::class.java.name)
    }
}