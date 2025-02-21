package com.techyourchance.dagger2course.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity

open class BaseDialog: DialogFragment() {
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.presentationComponent().create()
    }

    protected val injector: PresentationComponent get() = presentationComponent
}
