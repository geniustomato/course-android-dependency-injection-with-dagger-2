package com.techyourchance.dagger2course.screens.common.screensnavigator

interface ScreensNavigator {
    fun toQuestionDetails(questionId: String)
    fun navigateBack()
}
