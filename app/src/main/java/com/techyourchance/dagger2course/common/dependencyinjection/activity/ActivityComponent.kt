package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.app.Application
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.common.dependencyinjection.application.AppComponent
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun application(): Application

    fun stackoverflowApi(): StackoverflowApi

    fun fragmentManager(): FragmentManager

    fun layoutInflater(): LayoutInflater

    fun screensNavigator(): ScreensNavigator
}
