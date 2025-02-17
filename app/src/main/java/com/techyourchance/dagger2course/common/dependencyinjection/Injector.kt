package com.techyourchance.dagger2course.common.dependencyinjection

import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import java.lang.reflect.Field

class Injector(private val presentationCompositionRoot: PresentationCompositionRoot) {
    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitialValue = field.isAccessible

        with(field) {
            isAccessible = true
            set(client, getServiceForClass(type))
            isAccessible = isAccessibleInitialValue
        }
    }

    private fun getServiceForClass(type: Class<*>?): Any {
        return when (type) {
            DialogsNavigator::class.java -> {
                presentationCompositionRoot.dialogsNavigator
            }

            ScreensNavigator::class.java -> {
                presentationCompositionRoot.screensNavigator
            }

            FetchQuestionsUseCase::class.java -> {
                presentationCompositionRoot.fetchQuestionsUseCase
            }

            FetchQuestionDetailsUseCase::class.java -> {
                presentationCompositionRoot.fetchQuestionDetailsUseCase
            }

            ViewMvcFactory::class.java -> {
                presentationCompositionRoot.viewMvcFactory
            }

            else -> throw Exception("Unsupported type: $type")
        }
    }

    private fun isAnnotatedForInjection(field: Field): Boolean =
        field.annotations.any { it is Service }

    private fun getAllFields(client: Any): Array<out Field> = client::class.java.declaredFields

}