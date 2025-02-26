package com.techyourchance.dagger2course.common.dependencyinjection.application

import android.app.Application
import com.techyourchance.dagger2course.common.dependencyinjection.qualifier.StackoverflowRetrofit
import com.techyourchance.dagger2course.common.dependencyinjection.qualifier.RetrofitTestQualifier
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.common.imageloader.GlideImageLoader
import com.techyourchance.dagger2course.common.imageloader.ImageLoader
import com.techyourchance.dagger2course.networking.UrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides global objects that is shared anywhere in the application
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

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

    @Provides
    @Singleton
    fun imageLoader(application: Application): ImageLoader =
        GlideImageLoader(context = application)

    @Provides
    @Singleton
    fun stackoverflowApi(@StackoverflowRetrofit retrofit: Retrofit): StackoverflowApi =
        retrofit.create(StackoverflowApi::class.java)
}