package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityModule
import com.techyourchance.dagger2course.common.dependencyinjection.activity.DaggerActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent

open class BaseActivity : AppCompatActivity() {
    private val appComponent by lazy { (application as MyApplication).appComponent }

    private val activityComponent by lazy {
        DaggerActivityComponent.builder()
            .appComponent(appComponent)
            .activityModule(ActivityModule(activity = this))
            .build()
    }

    private val presentationComponent by lazy {
        activityComponent.presentationComponent()
    }

    protected val injector: PresentationComponent.Factory get() = presentationComponent
}
