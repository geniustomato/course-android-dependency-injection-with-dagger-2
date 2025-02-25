package com.techyourchance.dagger2course.screens.common.screensnavigator

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.viewmodel.ViewModelActivity
import javax.inject.Inject

class ScreensNavigatorImpl @Inject constructor(private val activity: AppCompatActivity) :
    ScreensNavigator {

    override fun toQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(context = activity, questionId = questionId)
    }

    override fun navigateBack() {
        activity.onBackPressed()
    }

    override fun toViewModel() {
        ViewModelActivity.start(context = activity)
    }
}
