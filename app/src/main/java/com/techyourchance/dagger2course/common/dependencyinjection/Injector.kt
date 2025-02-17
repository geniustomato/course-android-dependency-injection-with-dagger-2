package com.techyourchance.dagger2course.common.dependencyinjection

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListActivity

class Injector(private val presentationCompositionRoot: PresentationCompositionRoot) {
    fun inject(questionsListActivity: QuestionsListActivity) {
        with(questionsListActivity) {
            presentationCompositionRoot.let {
                screensNavigator = it.screensNavigator
                dialogsNavigator = it.dialogsNavigator
                fetchQuestionsUseCase = it.fetchQuestionsUseCase
                viewMvcFactory = it.viewMvcFactory
            }
        }
    }

    fun inject(questionDetailsActivity: QuestionDetailsActivity) {
        with(questionDetailsActivity) {
            presentationCompositionRoot.let {
                screensNavigator = it.screensNavigator
                dialogsNavigator = it.dialogsNavigator
                fetchQuestionDetailsUseCase = it.fetchQuestionDetailsUseCase
                viewMvcFactory = it.viewMvcFactory
            }
        }
    }
}