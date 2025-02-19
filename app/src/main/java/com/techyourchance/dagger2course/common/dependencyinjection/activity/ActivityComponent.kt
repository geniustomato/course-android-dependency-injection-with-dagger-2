package com.techyourchance.dagger2course.common.dependencyinjection.activity

import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun presentationComponent(): PresentationComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(activityModule: ActivityModule): ActivityComponent
    }
}
