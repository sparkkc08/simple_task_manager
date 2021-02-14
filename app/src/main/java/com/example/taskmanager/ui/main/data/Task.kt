package com.example.taskmanager.ui.main.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val userId: String,
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    val title: String,
    var completed: Boolean = false,
    var timeMillis: Long = 0
)
