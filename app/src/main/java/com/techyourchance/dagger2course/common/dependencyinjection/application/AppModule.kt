package com.techyourchance.dagger2course.common.dependencyinjection.application

import android.app.Application
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.common.dependencyinjection.qualifier.StackoverflowRetrofit
import com.techyourchance.dagger2course.common.dependencyinjection.qualifier.RetrofitTestQualifier
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.common.imageloader.GlideImageLoader
import com.techyourchance.dagger2course.common.imageloader.ImageLoader
import com.techyourchance.dagger2course.networking.UrlProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides global objects that is shared anywhere in the application
 */
@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    @StackoverflowRetrofit
    fun retrofit1(urlProvider: UrlProvider): Retrofit =
        Retrofit.Builder()
            .baseUrl(urlProvider.baseUrl1())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @RetrofitTestQualifier
    fun retrofit2(urlProvider: UrlProvider): Retrofit =
        Retrofit.Builder()
            .baseUrl(urlProvider.baseUrl2())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun urlProvider() = UrlProvider()

    @Singleton
    @Provides
    fun imageLoader(): ImageLoader =
        GlideImageLoader(context = application)

    @Provides
    fun application() = application

    @Singleton
    @Provides
    fun stackoverflowApi(@StackoverflowRetrofit retrofit: Retrofit): StackoverflowApi =
        retrofit.create(StackoverflowApi::class.java)
}