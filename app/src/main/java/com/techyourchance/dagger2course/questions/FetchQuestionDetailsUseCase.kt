package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.networking.StackoverflowApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import kotlin.coroutines.cancellation.CancellationException

class FetchQuestionDetailsUseCase(
    private val retrofit: Retrofit,
) {
    private val stackoverflowApi: StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

    sealed class Result {
        data class Success(val questionBody: String) : Result()
        data object Failure : Result()
    }

    suspend fun fetchQuestionDetails(questionId: String): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    val questionBody = response.body()!!.question.body
                    Result.Success(questionBody)
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
