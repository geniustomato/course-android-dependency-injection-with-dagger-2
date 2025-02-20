package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.users.User

data class QuestionDetailsDTO(
    val user: User,
    val questionBody: String
)
