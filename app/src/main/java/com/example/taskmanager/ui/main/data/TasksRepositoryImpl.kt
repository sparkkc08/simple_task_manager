package com.example.taskmanager.ui.main.data

import com.example.taskmanager.ui.main.network.RestApiService
import com.example.taskmanager.ui.main.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksRepositoryImpl(private val api: RestApiService, private val database: AppDatabase) :
    TasksRepository {
    override suspend fun getTasks(): List<Task> = withContext(Dispatchers.IO) {
        val tasks = api.getUserTasks(Constants.USER_ID)
        if (tasks.isNotEmpty()) {
            database.taskDao().insert(*tasks.toTypedArray())
        }

        database.taskDao().getAllTasks()
    }

    override suspend fun addTaskAndGetAll(task: Task): List<Task> = withContext(Dispatchers.IO) {
        // possible api call to add task on server
        database.taskDao().insertBeginAndGetAll(task)
    }

    override suspend fun updateTaskAndGetAll(task: Task): List<Task> = withContext(Dispatchers.IO) {
        // possible api call to update task on server
        database.taskDao().updateAndGetAll(task)
    }
}