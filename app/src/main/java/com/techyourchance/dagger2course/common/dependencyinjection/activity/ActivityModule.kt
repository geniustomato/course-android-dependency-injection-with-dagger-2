package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.app.Application
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides

/**
 * Provides object that are reused within an Activity's scope
 */
@Module
class ActivityModule(
    val activity: AppCompatActivity,
) {

    @Provides
    fun application(application: Application) = application

    @Provides
    fun stackoverflowApi(stackoverflowApi: StackoverflowApi) = stackoverflowApi

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun layoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun screensNavigator() = ScreensNavigator(activity = activity)
}
