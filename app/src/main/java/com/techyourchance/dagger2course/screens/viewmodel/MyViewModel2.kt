package com.techyourchance.dagger2course.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.questions.Question
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyViewModel2 @Inject constructor(
    private val fetchQuestionsUseCase: FetchQuestionsUseCase,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _questions: MutableLiveData<List<Question>> =
        savedStateHandle.getLiveData("questions")
    val question: LiveData<List<Question>> = _questions

    init {
        viewModelScope.launch {
            when (val result = fetchQuestionsUseCase.fetchLatestQuestions()) {
                is FetchQuestionsUseCase.Result.Success -> _questions.value = result.data
                FetchQuestionsUseCase.Result.Failure -> throw RuntimeException("Fetch failed")
            }
        }
    }
}
