package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.dependencyinjection.ActivityModule
import com.techyourchance.dagger2course.common.dependencyinjection.DaggerActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.DaggerPresentationComponent
import com.techyourchance.dagger2course.common.dependencyinjection.Injector
import com.techyourchance.dagger2course.common.dependencyinjection.PresentationModule

open class BaseActivity : AppCompatActivity() {
    private val appComponent by lazy { (application as MyApplication).appComponent }

    private val activityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(
                ActivityModule(
                    activity = this
                )
            )
            .build()
    }

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .appComponent(appComponent)
            .activityComponent(activityComponent)
            .presentationModule(PresentationModule())
            .build()

    }

    protected val injector: Injector get() = Injector(presentationComponent = presentationComponent)
}