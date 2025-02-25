package com.techyourchance.dagger2course.common.viewmodels

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel2
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val fetchQuestionsUseCaseProvider: Provider<FetchQuestionsUseCase>,
    savedStateRegistryOwner: SavedStateRegistryOwner,
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            MyViewModel::class.java -> MyViewModel(
                fetchQuestionsUseCase = fetchQuestionsUseCaseProvider.get(),
                savedStateHandle = handle
            ) as T
            MyViewModel2::class.java -> MyViewModel2(
                fetchQuestionsUseCase = fetchQuestionsUseCaseProvider.get(),
                savedStateHandle = handle
            ) as T
            else -> throw RuntimeException("Unsupported class: $modelClass")
        }
    }
}
