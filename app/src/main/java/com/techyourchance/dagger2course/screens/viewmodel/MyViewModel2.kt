package com.techyourchance.dagger2course.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.questions.Question
import com.techyourchance.dagger2course.screens.common.viewmodels.SavedStateViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val QUESTION_KEY = "questions"

class MyViewModel2 @Inject constructor(
    private val fetchQuestionsUseCase: FetchQuestionsUseCase,
) : SavedStateViewModel() {

    private lateinit var _questions: MutableLiveData<List<Question>>
    val question: LiveData<List<Question>> get() = _questions

    override fun init(savedStateHandle: SavedStateHandle) {
        _questions = savedStateHandle.getLiveData(QUESTION_KEY)
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