package com.techyourchance.dagger2course

import android.app.Application
import com.techyourchance.dagger2course.common.dependencyinjection.AppComponent
import com.techyourchance.dagger2course.common.dependencyinjection.AppModule
import com.techyourchance.dagger2course.common.dependencyinjection.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(application = this))
            .build()
    }
}