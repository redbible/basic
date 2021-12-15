package com.redbible.baseview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.redbible.baseview.Disposer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDataBindingBottomSheetFragment<B : ViewDataBinding>(private val layoutId: Int) :
    BottomSheetDialogFragment(),
    Disposer {

    protected lateinit var binding: B
    private val compositeDisposableOnPause = CompositeDisposable()
    private val compositeDisposableOnDestroy = CompositeDisposable()

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

    override fun onDestroy() {
        compositeDisposableOnDestroy.clear()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

        view?.post {
            val bottomSheetBehavior =
                ((view?.parent as View).layoutParams as CoordinatorLayout.LayoutParams).behavior as BottomSheetBehavior
            bottomSheetBehavior.peekHeight = activity?.window?.decorView?.measuredHeight!!
        }
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