package com.proxer.easydo.main.ui

import com.proxer.easydo.main.ui.model.Category
import com.proxer.easydo.main.ui.model.TaskModel

data class MainState(
    val isDrawerClosed: Boolean = true,
    val categories: List<Category> = createCategories(),
    val taskModels: List<TaskModel> = createTasks(),
    val error: Exception? = null
)

fun createCategories(): List<Category> {
    return listOf(
        Category(0, "Job", 0xFFC92BA6, 4, 1),
        Category(1, "Personal", 0xFF338D29, 2, 1),
        Category(2, "Other", 0xFF738534, 3, 2)
    )
}

fun createTasks(): List<TaskModel> = listOf(
    TaskModel(
        0,
        "Train guitar and drink vodka with my bear",
        0xFFC92BA6,
        isFocused = false,
        isCompleted = true,
        categoryId = 0
    ),
    TaskModel(
        1,
        "Wash dog's pinos",
        0xFF338D29,
        isFocused = false,
        isCompleted = false,
        categoryId = 1
    ),
    TaskModel(2, "Jump 30 circles", 0xFF738534, isFocused = false, isCompleted = true, categoryId = 2),
    TaskModel(3, "Wash my pinos", 0xFF338D29, isFocused = false, isCompleted = true, categoryId = 1),
    TaskModel(4, "Change design", 0xFFC92BA6, isFocused = false, isCompleted = false, categoryId = 0),
    TaskModel(5, "Update fonts", 0xFFC92BA6, isFocused = false, isCompleted = false, categoryId = 0),
    TaskModel(
        6,
        "Ask random guy to run away from dog",
        0xFF738534,
        isFocused = false,
        isCompleted = false,
        categoryId = 2
    ),
    TaskModel(7, "Watch a video", 0xFF738534, isFocused = false, isCompleted = true, categoryId = 2),
    TaskModel(8, "End application", 0xFFC92BA6, isFocused = false, isCompleted = false, categoryId = 0)
)