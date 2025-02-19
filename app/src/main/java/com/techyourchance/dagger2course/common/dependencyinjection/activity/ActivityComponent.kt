package com.techyourchance.dagger2course.common.dependencyinjection.activity

import com.techyourchance.dagger2course.common.dependencyinjection.application.AppComponent
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun presentationComponent(): PresentationComponent.Factory
}
