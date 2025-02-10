package com.techyourchance.dagger2course.questions

import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FetchQuestionsUseCaseTest {
    // TODO Incompatible for testing because we can't mock everything

    @MockK
    private val fetchQuestionsUseCase: FetchQuestionsUseCase = mockk()

    private val testData = listOf(
        Question(title = "Title 1", "id1"),
        Question(title = "Title 2", "id2"),
        Question(title = "Title 3", "id3"),
        Question(title = "Title 4", "id4"),
    )

    @Test
    fun `Should fetch questions correctly on success`() {
        val expected = FetchQuestionsUseCase.Result.Success(
            data = testData
        )

        coEvery { fetchQuestionsUseCase.fetchLatestQuestions() } returns expected

        runTest {
            val actual = fetchQuestionsUseCase.fetchLatestQuestions()
            assertEquals(expected, actual)
        }

    }

    @Test
    fun `Should prompt failure when fetch question fails`() {
        val expected = FetchQuestionsUseCase.Result.Failure

        coEvery { fetchQuestionsUseCase.fetchLatestQuestions() } returns expected

        runTest {
            val actual = fetchQuestionsUseCase.fetchLatestQuestions()
            assertEquals(expected, actual)

        }
    }
}
