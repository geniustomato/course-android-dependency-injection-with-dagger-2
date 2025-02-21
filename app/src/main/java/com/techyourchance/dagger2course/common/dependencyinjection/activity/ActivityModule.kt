package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.common.screensnavigator.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.screensnavigator.ScreensNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Provides object that are reused within an Activity's scope
 */
@Module
abstract class ActivityModule {

    @ActivityScope
    @Binds
    abstract fun screensNavigator(screensNavigatorImpl: ScreensNavigatorImpl): ScreensNavigator

    companion object {
        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

        @Provides
        fun layoutInflater(activity: AppCompatActivity): LayoutInflater =
            LayoutInflater.from(activity)
    }
}
