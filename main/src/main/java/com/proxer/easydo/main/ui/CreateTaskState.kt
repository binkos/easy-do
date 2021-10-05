package com.proxer.easydo.main.ui

data class CreateTaskState(
    val categories: List<TaskCategory> = createTaskCategories(),
    val isInProgress: Boolean = false
)

data class TaskCategory(
    val id: Int,
    val name: String,
    val color: Long,
    val isSelected: Boolean
)

fun createTaskCategories(): List<TaskCategory> =
    createCategories().map { TaskCategory(it.id, it.name, it.color, false) }.toMutableList()
