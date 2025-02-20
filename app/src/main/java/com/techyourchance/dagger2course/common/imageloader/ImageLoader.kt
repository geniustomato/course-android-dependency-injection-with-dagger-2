package com.techyourchance.dagger2course.common.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(imageUrl: String, imageView: ImageView)
}
