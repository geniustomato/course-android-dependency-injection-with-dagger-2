package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides

/**
 * Provides object that are reused within an Activity's scope
 */
@Module
object ActivityModule {
    @Provides
    fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater =
        LayoutInflater.from(activity)

    @ActivityScope
    @Provides
    fun screensNavigator(activity: AppCompatActivity) = ScreensNavigator(activity = activity)
}
