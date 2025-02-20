package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.networking.StackoverflowApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class FetchQuestionDetailsUseCase @Inject constructor(
    private val stackoverflowApi: StackoverflowApi,
) {
    sealed class Result {
        data class Success(val questionDetails: QuestionDetailsDTO) : Result()
        data object Failure : Result()
    }

    suspend fun fetchQuestionDetails(questionId: String): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.question!!.let { question ->
                        Result.Success(
                            QuestionDetailsDTO(
                                user = question.user,
                                questionBody = question.body
                            )
                        )
                    }
                } else {
                    Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}
