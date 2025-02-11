package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.networking.SingleQuestionResponseSchema
import com.techyourchance.dagger2course.networking.StackoverflowApi
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response
import retrofit2.Retrofit

class FetchQuestionDetailsUseCaseTest {

    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    @MockK
    private lateinit var stackOverflowApi: StackoverflowApi

    @MockK
    private lateinit var retrofit: Retrofit

    private val testData =
        QuestionWithBody(
            title = "title",
            id = "id",
            body = "body",
        )


    @BeforeEach
    fun setUp() {
        coEvery { stackOverflowApi.questionDetails(questionId = any()) } returns Response.success(
            SingleQuestionResponseSchema(questions = listOf(testData))
        )

        fetchQuestionDetailsUseCase = FetchQuestionDetailsUseCase(retrofit = retrofit)
    }

    @Test
    fun `Should fetch question details successfully`() {
        val expected = FetchQuestionDetailsUseCase.Result.Success(questionBody = testData.body)
        val questionId = "id"

        coEvery { fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId = questionId) } returns expected

        runTest {
            val actual = fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId = questionId)
            assertEquals(expected, actual)
        }
    }
}
