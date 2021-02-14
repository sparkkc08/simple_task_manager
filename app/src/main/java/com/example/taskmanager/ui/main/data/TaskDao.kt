package com.example.taskmanager.ui.main.data

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY timeMillis DESC")
    suspend fun getAllTasks(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg task: Task): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Transaction
    suspend fun insertBeginAndGetAll(task: Task): List<Task> {
        insert(task)
        return getAllTasks()
    }

    @Transaction
    suspend fun updateAndGetAll(task: Task): List<Task> {
        update(task)
        return getAllTasks()
    }
}