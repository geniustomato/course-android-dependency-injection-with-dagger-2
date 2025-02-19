package com.techyourchance.dagger2course.common.dependencyinjection.application

import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun activityComponent(): ActivityComponent.Factory
}
