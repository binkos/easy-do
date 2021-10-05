package com.proxer.easydo.main.ui.model

data class TaskModel(
    val id: Int,
    val name: String,
    val color: Long,
    val isFocused: Boolean,
    val isCompleted: Boolean,
    val categoryId: Int
)