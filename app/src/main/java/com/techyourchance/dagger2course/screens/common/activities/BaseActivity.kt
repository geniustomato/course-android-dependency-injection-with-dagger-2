package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {
    private val appComponent by lazy { (application as MyApplication).appComponent }

    val activityComponent by lazy {
        appComponent
            .activityComponent()
            .activity(activity = this)
            .build()
    }

    private val presentationComponent by lazy {
        activityComponent.presentationComponent().create(PresentationModule(savedStateRegistryOwner = this))
    }

    protected val injector: PresentationComponent get() = presentationComponent
}
