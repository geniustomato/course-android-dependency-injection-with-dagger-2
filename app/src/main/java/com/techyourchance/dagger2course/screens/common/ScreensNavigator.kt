package com.techyourchance.dagger2course.screens.common

import android.app.Activity
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigator(private val activity: Activity) {

    fun toQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(context = activity, questionId = questionId)
    }

    fun navigateBack() {
        activity.onBackPressed()
    }
}