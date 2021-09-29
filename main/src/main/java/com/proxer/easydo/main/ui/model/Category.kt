package com.proxer.easydo.main.ui.model

data class Category(
    val id: Int,
    val name: String,
    val color: Long,
    val allTasks: Int,
    val completedTasks: Int
)