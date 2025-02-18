package com.techyourchance.dagger2course.common.dependencyinjection

import android.app.Application
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provides global objects that is shared anywhere in the application
 */
@Module
class AppModule(val application: Application) {

    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun stackoverflowApi(retrofit: Retrofit): StackoverflowApi =
        retrofit.create(StackoverflowApi::class.java)
}