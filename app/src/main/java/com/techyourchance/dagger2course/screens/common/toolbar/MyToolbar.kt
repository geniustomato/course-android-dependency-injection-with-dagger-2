package com.techyourchance.dagger2course.screens.common.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.techyourchance.dagger2course.R

class MyToolbar : Toolbar {

    private var navigateUpListener: () -> Unit = {}
    private var viewModelListener: () -> Unit = {}

    private lateinit var navigateUp: FrameLayout
    private lateinit var viewModel: TextView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_my_toolbar, this, true)
        setContentInsetsRelative(0, 0)
        navigateUp = view.findViewById(R.id.navigate_up)
        navigateUp.setOnClickListener { navigateUpListener.invoke() }
        viewModel = view.findViewById(R.id.viewmodel)
        viewModel.setOnClickListener { viewModelListener.invoke() }
    }

    fun setNavigateUpListener(navigateUpListener: () -> Unit) {
        this.navigateUpListener = navigateUpListener
        navigateUp.visibility = View.VISIBLE
    }

    fun setViewModelListener(viewModelListener: () -> Unit) {
        this.viewModelListener = viewModelListener
        viewModel.visibility = View.VISIBLE
    }
}