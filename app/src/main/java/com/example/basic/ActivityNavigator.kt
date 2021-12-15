package com.example.basic

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.example.basic.ui.MainActivity
import com.example.basic.ui.SecondActivity
import java.util.*

/**
 * UI component 이외의 곳 (ViewModel, Service, 등) 에서 사용하지 않도록 주의할 것.
 * context 에 대한 reference 가 풀리지 않아 memory leak 이 발생할 수 있음.
 */
class ActivityNavigator private constructor(private val context: Context) {

    companion object {
        const val KEY_DATA = "data"

        @JvmStatic
        fun with(context: Context): ActivityNavigator = ActivityNavigator(context)

        @JvmStatic
        fun with(fragment: Fragment): ActivityNavigator = with(
            fragment.context
                ?: fragment.requireActivity()
        )
    }

    val stack: ArrayList<Intent> = ArrayList()

    fun main() =
        MyIntent(MainActivity::class.java)

    fun second(data: String = "") =
        MyIntent(SecondActivity::class.java).apply {
            putExtra(KEY_DATA, data)
        }

    inner class MyIntent : Intent {

        constructor(cls: Class<*>?) : super(context, cls) {
            addFlags(FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT)
        }

        constructor(action: String?, uri: Uri?) : super(action, uri)

        fun add(): ActivityNavigator {
            stack.add(this)
            return this@ActivityNavigator
        }

        fun start() {
            stack.add(this)
            context.startActivities(stack.toTypedArray())
        }

        fun startForResult(requestCode: Int) {
            if (context !is Activity) {
                throw IllegalArgumentException("startActivityForResult 는 context 가 activity 일 경우에만 사용할 수 있습니다.")
            }

            context.startActivityForResult(this, requestCode)
        }
    }
}