package com.techyourchance.dagger2course.common.dependencyinjection.presentation

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListActivity
import com.techyourchance.dagger2course.screens.viewmodel.ViewModelActivity
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelsModule::class])
interface PresentationComponent {
    fun inject(client: QuestionsListActivity)
    fun inject(client: QuestionDetailsActivity)
    fun inject(client: ViewModelActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): PresentationComponent
    }
}
