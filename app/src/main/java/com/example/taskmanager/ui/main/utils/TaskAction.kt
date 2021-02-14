package com.example.taskmanager.ui.main.utils

import com.example.taskmanager.ui.main.model.Task

interface TaskAction {
    fun onChecked(task: Task, isChecked: Boolean)
}