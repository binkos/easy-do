package com.proxer.easydo.main.ui

import com.proxer.easydo.main.ui.model.TaskModel

sealed class CreateTaskEvent {
    class SelectCategory(val category: TaskCategory) : CreateTaskEvent()
    class CreateTask(val taskModel: TaskModel) : CreateTaskEvent()
}