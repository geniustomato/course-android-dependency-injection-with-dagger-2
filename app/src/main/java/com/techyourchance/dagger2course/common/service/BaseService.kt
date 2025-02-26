package com.techyourchance.dagger2course.common.service

import android.app.Service

abstract class BaseService : Service() {

    val serviceComponent by lazy {
    }
}
