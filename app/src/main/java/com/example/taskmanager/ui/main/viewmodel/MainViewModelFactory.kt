package com.example.taskmanager.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.ui.main.data.TasksRepository

class MainViewModelFactory(private val tasksRepository: TasksRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(tasksRepository) as T
    }
}