package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.composition.ActivityCompositionRoot

open class BaseActivity : AppCompatActivity() {
    val compositionRoot by lazy {
        ActivityCompositionRoot(
            activity = this,
            appCompositionRoot = (application as MyApplication).appCompositionRoot
        )
    }
}