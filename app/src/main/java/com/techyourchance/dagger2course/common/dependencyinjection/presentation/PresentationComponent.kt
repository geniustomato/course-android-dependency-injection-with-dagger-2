package com.techyourchance.dagger2course.common.dependencyinjection.presentation

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListActivity
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, UseCasesModule::class])
interface PresentationComponent {
    fun inject(client: QuestionsListActivity)
    fun inject(client: QuestionDetailsActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(presentationModule: PresentationModule, useCasesModule: UseCasesModule): PresentationComponent
    }
}
