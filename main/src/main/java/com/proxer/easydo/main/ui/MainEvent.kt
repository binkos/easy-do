package com.proxer.easydo.main.ui

import com.proxer.easydo.main.ui.model.Task

sealed class MainEvent private constructor() {
    object ClickOnDrawer : MainEvent()
    object CreateNewTask : MainEvent()
    object CreateNewCategory : MainEvent()
    class SelectCategory(val id: Int) : MainEvent()
    class CompleteTask(val task: Task) : MainEvent()
//    class SelectCategory(id: Int) : MainEvent()
}

