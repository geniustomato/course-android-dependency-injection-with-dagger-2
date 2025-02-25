package com.techyourchance.dagger2course.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel2
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val myViewModelProvider: Provider<MyViewModel>,
    private val myViewModelProvider2: Provider<MyViewModel2>,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MyViewModel::class.java -> myViewModelProvider.get() as T
            MyViewModel2::class.java -> myViewModelProvider2.get() as T
            else -> throw RuntimeException("Unsupported View Model class: $modelClass")
        }
    }
}