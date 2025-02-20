package com.techyourchance.dagger2course.screens.common.viewmvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

open class BaseViewMvc<LISTENER_TYPE>(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?,
    @LayoutRes private val layoutRes: Int
) {
    val rootView: View = layoutInflater.inflate(layoutRes, parent, false)

    protected val listeners = HashSet<LISTENER_TYPE>()

    protected fun <T : View?> findViewById(@IdRes id: Int): T = rootView.findViewById<T>(id)

    protected val context: Context get() = rootView.context

    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }
}
