package com.example.taskmanager.ui.main.utils

import android.view.View
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

@BindingMethods(
    BindingMethod(
        type = View::class,
        attribute = "background",
        method = "setBackgroundResource"
    )
)
class BindingMethods