package com.techyourchance.dagger2course.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.questions.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val QUESTION_KEY = "questions"

@HiltViewModel
class MyViewModel @Inject constructor(
    private val fetchQuestionsUseCase: FetchQuestionsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _questions: MutableLiveData<List<Question>> = savedStateHandle.getLiveData(QUESTION_KEY)
    val question: LiveData<List<Question>> get() = _questions

    init {
        fun isStateSaved() = savedStateHandle.get<Question>(QUESTION_KEY) != null


        if (!isStateSaved()) {
            viewModelScope.launch {
                when (val result = fetchQuestionsUseCase.fetchLatestQuestions()) {
                    is FetchQuestionsUseCase.Result.Success -> _questions.value = result.data
                    FetchQuestionsUseCase.Result.Failure -> throw RuntimeException("Fetch failed")
                }
            }
        }
    }


}
