package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.networking.QuestionsListResponseSchema
import com.techyourchance.dagger2course.networking.StackoverflowApi
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

class FetchQuestionsUseCaseTest {
    private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase

    @MockK
    private val stackoverflowApi: StackoverflowApi = mockk()

    private val testData = listOf(
        Question(title = "Title 1", "id1"),
        Question(title = "Title 2", "id2"),
        Question(title = "Title 3", "id3"),
        Question(title = "Title 4", "id4"),
    )

    @BeforeEach
    fun setUp() {
        fetchQuestionsUseCase = FetchQuestionsUseCase(stackoverflowApi = stackoverflowApi)
    }

    @Test
    fun `Should fetch questions correctly on success`() {
        val expected = FetchQuestionsUseCase.Result.Success(
            data = testData
        )

        coEvery { stackoverflowApi.lastActiveQuestions(any()) } returns Response.success(
            QuestionsListResponseSchema(
                questions = testData
            )
        )

        runTest {
            val actual = fetchQuestionsUseCase.fetchLatestQuestions()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Should prompt failure when fetch question returns success with empty response body`() {
        val expected = FetchQuestionsUseCase.Result.Failure

        coEvery { stackoverflowApi.lastActiveQuestions(any()) } returns Response.success(null)

        runTest {
            val actual = fetchQuestionsUseCase.fetchLatestQuestions()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Should prompt failure when fetch question fails`() {
        val expected = FetchQuestionsUseCase.Result.Failure

        coEvery { stackoverflowApi.lastActiveQuestions(any()) } returns Response.error(
            404,
            ResponseBody.create(null, "")
        )

        runTest {
            val actual = fetchQuestionsUseCase.fetchLatestQuestions()
            assertEquals(expected, actual)

        }
    }

    @Test
    fun `Should prompt failure when fetch question throws an exception`() {
        val expected = FetchQuestionsUseCase.Result.Failure

        coEvery { stackoverflowApi.lastActiveQuestions(any()) } throws RuntimeException()

        runTest {
            val actual = fetchQuestionsUseCase.fetchLatestQuestions()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Should throw exception when fetch question throws CancellationException`() {
        coEvery { stackoverflowApi.lastActiveQuestions(any()) } throws CancellationException()

        runTest {
            assertThrows<CancellationException>(message = { "Throwing Cancellation Exception"}) { fetchQuestionsUseCase.fetchLatestQuestions() }
        }
    }
}
