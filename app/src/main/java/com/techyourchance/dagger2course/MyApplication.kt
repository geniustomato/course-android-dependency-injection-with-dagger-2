package com.techyourchance.dagger2course

import android.app.Application
import com.techyourchance.dagger2course.common.dependencyinjection.application.AppComponent
import com.techyourchance.dagger2course.common.dependencyinjection.application.AppModule
import com.techyourchance.dagger2course.common.dependencyinjection.application.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(application = this))
            .build()
    }
}