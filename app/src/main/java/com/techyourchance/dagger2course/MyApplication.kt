package com.techyourchance.dagger2course

import android.app.Application
import com.techyourchance.dagger2course.common.composition.AppCompositionRoot

class MyApplication : Application() {

    val appCompositionRoot: AppCompositionRoot = AppCompositionRoot()

    override fun onCreate() {
        super.onCreate()
    }
}