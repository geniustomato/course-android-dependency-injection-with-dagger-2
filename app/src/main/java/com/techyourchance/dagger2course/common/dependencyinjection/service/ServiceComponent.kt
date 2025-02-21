package com.techyourchance.dagger2course.common.dependencyinjection.service

import dagger.Subcomponent

@Subcomponent(modules = [ServiceModule::class])
interface ServiceComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(serviceModule: ServiceModule): ServiceComponent
    }

}