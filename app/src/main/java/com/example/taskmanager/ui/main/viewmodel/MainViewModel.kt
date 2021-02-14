package com.example.taskmanager.ui.main.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.ui.main.data.Task
import com.example.taskmanager.ui.main.data.TasksRepository
import com.example.taskmanager.ui.main.ui.TasksAdapter
import com.example.taskmanager.ui.main.utils.Constants
import com.example.taskmanager.ui.main.utils.TaskAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val tasksRepository: TasksRepository) : ViewModel(), TaskAction {

    val newTask = ObservableField<String>()

    val adapter = TasksAdapter(this)

    fun fetchTasks() {
        viewModelScope.launch {
            val tasks = tasksRepository.getTasks()
            adapter.submitList(tasks)
        }
    }

    fun onAddClick() {
        val newTaskTitle = newTask.get()
        if (!newTaskTitle.isNullOrBlank()) {
            viewModelScope.launch {
                val tasks = tasksRepository.addTaskAndGetAll(
                    Task(
                        Constants.USER_ID,
                        null,
                        newTaskTitle,
                        false,
                        System.currentTimeMillis()
                    )
                )

                withContext(Dispatchers.Main) {
                    newTask.set("")
                    adapter.submitList(tasks)
                }
            }
        }
    }

    override fun onTaskUpdate(task: Task) {
        viewModelScope.launch {
            val tasks = tasksRepository.updateTaskAndGetAll(task)
            adapter.submitList(tasks)
        }
    }
}