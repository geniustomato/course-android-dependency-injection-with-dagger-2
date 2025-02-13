package com.techyourchance.dagger2course.screens.common.viewmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsViewMvc
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListViewMvc

class ViewMvcFactory(
    private val layoutInflater: LayoutInflater,
) {
    fun newQuestionsListMvc(parent: ViewGroup?): QuestionsListViewMvc {
        return QuestionsListViewMvc(layoutInflater = layoutInflater, parent = parent)
    }

    fun newQuestionDetailsMvc(parent: ViewGroup?): QuestionDetailsViewMvc {
        return QuestionDetailsViewMvc(layoutInflater = layoutInflater, parent = parent)
    }
}
