package redbible.basic.common.ui.viewmodel

import android.arch.lifecycle.*
import coinone.co.kr.official.common.ui.fragment.BaseFragment

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreated() {
        //do nothing
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        //do nothing
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        //do nothing
    }

    open fun onFocusIn() {
        //do nothing
    }

    open fun onFocusOut() {
        //do nothing
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
    }

    fun bindLifecycle(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    fun bindLifecycle(owner: BaseFragment) {
        owner.lifecycleObservers.clear()
        owner.lifecycleObservers.add {
            when (it) {
                Lifecycle.Event.ON_CREATE -> onCreated()
                Lifecycle.Event.ON_START -> onStart()
                Lifecycle.Event.ON_RESUME -> onResume()
                Lifecycle.Event.ON_PAUSE -> onPause()
                Lifecycle.Event.ON_STOP -> onStop()
                Lifecycle.Event.ON_DESTROY -> onDestroy()
                else -> {
                }
            }
        }

        owner.hasFocusObservers.add {
            if (it) {
                onFocusIn()
            } else {
                onFocusOut()
            }
        }
    }
}