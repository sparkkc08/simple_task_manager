package com.example.taskmanager.ui.main.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.ui.main.network.RestApiService
import com.example.taskmanager.ui.main.model.Task
import com.example.taskmanager.ui.main.ui.TasksAdapter
import com.example.taskmanager.ui.main.utils.Constants
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel : ViewModel() {

    val newTask = ObservableField<String>()

    val adapter = TasksAdapter()

    fun fetchTasks() {
        viewModelScope.launch {
            val tasks = RestApiService.createService()
                .getUserTasks(Constants.USER_ID)
            adapter.submitList(tasks)
        }
    }

    fun onAddClick() {
        val newTaskTitle = newTask.get()
        if (!newTaskTitle.isNullOrBlank()) {
            adapter.addTask(
                Task(
                    Constants.USER_ID,
                    UUID.randomUUID().toString(),
                    newTaskTitle,
                    false
                )
            )
            newTask.set("")
        }
    }
}