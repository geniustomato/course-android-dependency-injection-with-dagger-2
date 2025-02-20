package com.techyourchance.dagger2course.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.common.imageloader.ImageLoader
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import com.techyourchance.dagger2course.screens.common.viewmvc.BaseViewMvc
import com.techyourchance.dagger2course.users.User

class QuestionDetailsViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    val imageLoader: ImageLoader
) : BaseViewMvc<QuestionDetailsViewMvc.Listener>(
    layoutInflater = layoutInflater,
    parent = parent,
    layoutRes = R.layout.layout_question_details
) {

    interface Listener {
        fun onNavigationUp()
    }

    private var toolbar: MyToolbar = findViewById(R.id.toolbar)
    private var swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swipeRefresh)
    private var txtQuestionBody: TextView = findViewById(R.id.txt_question_body)
    private var userImage: ImageView = findViewById(R.id.image_user_picture)
    private var userName: TextView = findViewById(R.id.text_user_name)

    init {
        toolbar.setNavigateUpListener {
            for (listener in listeners) {
                listener.onNavigationUp()
            }
        }

        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh.isEnabled = false
    }

    fun displayUserDetails(user: User) {
        userName.text = user.name
        imageLoader.loadImage(imageUrl = user.imageUrl, imageView = userImage)
    }

    fun displayQuestionBody(questionBody: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtQuestionBody.text =
                Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(questionBody)
        }
    }

    fun toggleProgressIndication(show: Boolean) {
        swipeRefresh.isRefreshing = show
    }

}