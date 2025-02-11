package com.techyourchance.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionDetailsActivity : AppCompatActivity(), QuestionDetailsViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var questionId: String
    private lateinit var viewMvc: QuestionDetailsViewMvc

    private val fetchQuestionDetailsUseCase by lazy { FetchQuestionDetailsUseCase((application as MyApplication).retrofit) }
    private val dialogsNavigator = DialogsNavigator(fragmentManager = supportFragmentManager)
    private val screensNavigator = ScreensNavigator(activity = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMvc = QuestionDetailsViewMvc(layoutInflater = LayoutInflater.from(this), parent = null)
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
        onBackPressed()
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
