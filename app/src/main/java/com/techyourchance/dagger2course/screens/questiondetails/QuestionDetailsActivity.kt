package com.techyourchance.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val viewMvc by lazy { compositionRoot.viewMvcFactory.newQuestionDetailsMvc(parent = null) }
    private val dialogsNavigator by lazy { compositionRoot.dialogsNavigator }
    private val screensNavigator by lazy { compositionRoot.screensNavigator }
    private val fetchQuestionDetailsUseCase by lazy { compositionRoot.fetchQuestionDetailsUseCase }

    private lateinit var questionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewMvc.rootView)

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(listener = this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(listener = this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onNavigationUp() {
        screensNavigator.navigateBack()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            viewMvc.toggleProgressIndication(show = true)
            try {
                when (val result =
                    fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId = questionId)) {
                    is FetchQuestionDetailsUseCase.Result.Success -> viewMvc.displayQuestionBody(
                        result.questionBody
                    )

                    FetchQuestionDetailsUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                viewMvc.toggleProgressIndication(show = false)
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }
}
