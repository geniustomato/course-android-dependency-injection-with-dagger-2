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
    @ActivityScope
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    @ActivityScope
    fun layoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    @ActivityScope
    fun screensNavigator() = ScreensNavigator(activity = activity)
}
