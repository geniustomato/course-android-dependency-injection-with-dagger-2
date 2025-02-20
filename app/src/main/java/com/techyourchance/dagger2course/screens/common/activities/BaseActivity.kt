package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityModule
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent

open class BaseActivity : AppCompatActivity() {
    private val appComponent by lazy { (application as MyApplication).appComponent }

    private val activityComponent by lazy {
        appComponent
            .activityComponent()
            .create(
                ActivityModule(activity = this)
            )
    }

    private val presentationComponent by lazy {
        activityComponent.presentationComponent().create()
    }

    protected val injector: PresentationComponent get() = presentationComponent
}
