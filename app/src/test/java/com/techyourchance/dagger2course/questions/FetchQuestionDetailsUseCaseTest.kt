package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.networking.SingleQuestionResponseSchema
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase.Result
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import retrofit2.Response
import kotlin.coroutines.cancellation.CancellationException

class FetchQuestionDetailsUseCaseTest {

    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    @MockK
    private val stackoverflowApi: StackoverflowApi = mockk()

    private val testData =
        QuestionWithBody(
            title = "title",
            id = "id",
            body = "body",
        )

    private val questionId = "id"



    @BeforeEach
    fun setUp() {
        fetchQuestionDetailsUseCase = FetchQuestionDetailsUseCase(stackoverflowApi = stackoverflowApi)
    }

    @Test
    fun `Should fetch question details successfully`() {
        val expected = Result.Success(questionBody = testData.body)
        val questionId = "id"

        coEvery { stackoverflowApi.questionDetails(questionId = any()) } returns Response.success(
            SingleQuestionResponseSchema(questions = listOf(testData))
        )

        runTest {
            val actual = fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId = questionId)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Should prompt failure when fetch question details returns null response body`() {
        val expected = Result.Failure

        coEvery { stackoverflowApi.questionDetails(questionId = any()) } returns Response.success(null)

        runTest {
            val actual = fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId = questionId)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Should prompt failure when fetch question details fails`() {
        val expected = Result.Failure

        coEvery { stackoverflowApi.questionDetails(any()) } returns Response.error(
            404,
            ResponseBody.create(null, "")
        )

        runTest {
            val actual = fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId = questionId)
            assertEquals(expected, actual)

        }
    }

    @Test
    fun `Should prompt failure when fetch question details throws an exception`() {
        val expected = Result.Failure

        coEvery { stackoverflowApi.questionDetails(any()) } throws RuntimeException()

        runTest {
            val actual = fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId = questionId)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Should throw exception when fetch question details throws CancellationException`() {
        coEvery { stackoverflowApi.questionDetails(any()) } throws CancellationException()

        runTest {
            assertThrows<CancellationException>(message = { "Throwing Cancellation Exception"}) { fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId = questionId) }
        }
    }
}
