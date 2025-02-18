package com.techyourchance.dagger2course.common.dependencyinjection.presentation

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import dagger.Module
import dagger.Provides

/**
 * Provides objects that are used by the Presentation layer
 */
@Module
class PresentationModule {

    @PresentationScope
    @Provides
    fun fetchQuestionsUseCase(stackoverflowApi: StackoverflowApi) =
        FetchQuestionsUseCase(stackoverflowApi = stackoverflowApi)

    @PresentationScope
    @Provides
    fun fetchQuestionDetailsUseCase(stackoverflowApi: StackoverflowApi) =
        FetchQuestionDetailsUseCase(stackoverflowApi = stackoverflowApi)

    @PresentationScope
    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) =
        DialogsNavigator(fragmentManager = fragmentManager)

    @PresentationScope
    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) =
        ViewMvcFactory(layoutInflater = layoutInflater)
}
