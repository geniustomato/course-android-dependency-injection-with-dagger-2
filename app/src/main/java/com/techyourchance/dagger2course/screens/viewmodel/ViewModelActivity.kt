package com.techyourchance.dagger2course.screens.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.common.viewmodels.ViewModelFactory
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.screensnavigator.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import javax.inject.Inject

class ViewModelActivity : BaseActivity() {

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var myViewModelFactory: ViewModelFactory

    private lateinit var myViewModel: MyViewModel
    private lateinit var myViewModel2: MyViewModel2

    private lateinit var toolbar: MyToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_view_model)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener {
            screensNavigator.navigateBack()
        }

        myViewModel = ViewModelProvider(this, myViewModelFactory)[MyViewModel::class.java]
        myViewModel2 = ViewModelProvider(this, myViewModelFactory)[MyViewModel2::class.java]

        myViewModel.question.observe(this) {
            Toast.makeText(
                this,
                it?.let { "Fetch Successful" } ?: "Fetch Failed",
                Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ViewModelActivity::class.java)
            context.startActivity(intent)
        }
    }
}
