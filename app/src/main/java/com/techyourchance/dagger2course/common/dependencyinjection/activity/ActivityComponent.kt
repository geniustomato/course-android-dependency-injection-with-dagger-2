package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun fragmentManager(): FragmentManager

    fun layoutInflater(): LayoutInflater

    fun screensNavigator(): ScreensNavigator
}
