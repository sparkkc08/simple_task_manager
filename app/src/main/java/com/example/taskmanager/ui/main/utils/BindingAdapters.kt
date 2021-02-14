package com.example.taskmanager.ui.main.utils

import android.graphics.Paint
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["textColor"])
fun setTextColor(textView: TextView, @ColorRes colorId: Int) {
    textView.setTextColor(ContextCompat.getColor(textView.context, colorId))
}

@BindingAdapter("taskComplete")
fun setTaskComplete(textView: TextView, isComplete: Boolean) {
    textView.paintFlags =
        if (isComplete) textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG else textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}