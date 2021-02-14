package com.example.taskmanager.ui.main.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.taskmanager.R
import com.example.taskmanager.ui.main.model.Task
import com.example.taskmanager.ui.main.utils.TaskAction

class TaskItemViewModel(private val task: Task, private val actionListener: TaskAction) :
    ViewModel() {

    val title = ObservableField(task.title)
    val titleColor = ObservableInt(if (task.completed) R.color.grey else R.color.black)
    val isCompleted = ObservableBoolean(task.completed)
    val isSingleLine = ObservableBoolean(true)

    val background =
        ObservableInt(if (task.completed) R.color.green else R.color.white)

    fun onItemChecked(isChecked: Boolean) {
        actionListener.onChecked(task, isChecked)
    }

    fun onTaskClicked() {
        isSingleLine.set(!isSingleLine.get())
    }
}