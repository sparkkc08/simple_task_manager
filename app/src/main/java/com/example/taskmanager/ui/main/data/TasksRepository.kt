package com.example.taskmanager.ui.main.data

interface TasksRepository {
    suspend fun getTasks(): List<Task>
    suspend fun addTaskAndGetAll(task: Task): List<Task>
    suspend fun updateTaskAndGetAll(task: Task): List<Task>
}