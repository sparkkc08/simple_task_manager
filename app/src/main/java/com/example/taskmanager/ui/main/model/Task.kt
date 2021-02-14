package com.example.taskmanager.ui.main.model

data class Task(
    val userId: String,
    val id: String,
    val title: String,
    var completed: Boolean
)
