package com.techyourchance.dagger2course.common.composition

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot,
) {
    private val stackoverflowApi get() = appCompositionRoot.stackoverflowApi
    private val fragmentManager get() = activity.supportFragmentManager

    val screensNavigator by lazy { ScreensNavigator(activity = activity) }
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi = stackoverflowApi)
    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi = stackoverflowApi)
    val dialogsNavigator get() = DialogsNavigator(fragmentManager = fragmentManager)
}
