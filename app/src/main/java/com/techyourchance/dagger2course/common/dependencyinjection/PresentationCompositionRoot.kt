package com.techyourchance.dagger2course.common.dependencyinjection

import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory

/**
 * Provides objects that are used by the Presentation layer
 */
class PresentationCompositionRoot(
    private val activityCompositionRoot: ActivityCompositionRoot,
) {
    private val stackoverflowApi get() = activityCompositionRoot.stackoverflowApi
    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    private val fragmentManager get() = activityCompositionRoot.fragmentManager

    val screensNavigator get() =  activityCompositionRoot.screensNavigator
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi = stackoverflowApi)
    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi = stackoverflowApi)
    val dialogsNavigator get() = DialogsNavigator(fragmentManager = fragmentManager)
    val viewMvcFactory get() = ViewMvcFactory(layoutInflater = layoutInflater)
}
