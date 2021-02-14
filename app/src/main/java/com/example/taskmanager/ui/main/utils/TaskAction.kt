package com.example.taskmanager.ui.main.utils

import com.example.taskmanager.ui.main.data.Task

interface TaskAction {
    fun onTaskUpdate(task: Task)
}