package coinone.co.kr.official.common.ui.fragment

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment(private val layoutId: Int, private val focusToResume: Boolean = false) : Fragment() {

    val hasFocusObservers = mutableListOf<(Boolean) -> Unit>()
    val lifecycleObservers = mutableListOf<(Lifecycle.Event) -> Unit>()

    private var isCreated = false
    private var isFocused = false
    private var isVisibleToUser = true
    private var hasFocus = false

    protected abstract fun setupView(view: View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupProperties(arguments)

        val view = createView(inflater, container)

        setupView(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isCreated = true
        notifyLifeCycleUpdate(Lifecycle.Event.ON_CREATE)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        notifyLifeCycleUpdate(Lifecycle.Event.ON_START)
    }

    final override fun onResume() {
        super.onResume()
        runOnResume()
        isFocused = true
    }

    open fun runOnResume() {
        notifyLifeCycleUpdate(Lifecycle.Event.ON_RESUME)
        if (!hasFocus) {
            updateFocusState(!isVisibleToUser)
        }
    }

    final override fun onPause() {
        isFocused = false
        runOnPause()
        super.onPause()
    }

    open fun runOnPause() {
        if (hasFocus) {
            updateFocusState(isVisibleToUser)
        }
        notifyLifeCycleUpdate(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        notifyLifeCycleUpdate(Lifecycle.Event.ON_STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        notifyLifeCycleUpdate(Lifecycle.Event.ON_DESTROY)
        isCreated = false
        super.onDestroyView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        updateFocusState(focusToResume)
    }

    protected open fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(layoutId, container, false)
    }

    protected open fun setupProperties(bundle: Bundle?) {
        //do nothing
    }

    protected open fun onFocusChanged(isFocused: Boolean) {
        //do nothing
    }

    private fun updateFocusState(focusToResume: Boolean) {
        if (!isCreated) {
            return
        }

        hasFocus = isFocused && isVisibleToUser
        if (focusToResume && hasFocus) {
            runOnResume()
        }
        onFocusChanged(hasFocus)
        hasFocusObservers.forEach { it.invoke(hasFocus) }
        if (focusToResume && !hasFocus) {
            runOnPause()
        }
    }

    private fun notifyLifeCycleUpdate(event: Lifecycle.Event) {
        lifecycleObservers.forEach { it.invoke(event) }
    }
}