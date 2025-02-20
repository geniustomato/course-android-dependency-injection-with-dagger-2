package com.techyourchance.dagger2course.common.dependencyinjection.presentation

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListActivity
import dagger.Subcomponent

@Subcomponent
interface PresentationComponent {
    fun inject(client: QuestionsListActivity)
    fun inject(client: QuestionDetailsActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): PresentationComponent
    }
}
