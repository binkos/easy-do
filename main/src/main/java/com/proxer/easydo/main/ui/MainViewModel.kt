package com.proxer.easydo.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    fun sendEvent(event: MainEvent) {
        viewModelScope.launch {
            when (event) {
                MainEvent.ClickOnDrawer -> {
                    val newState = state.value.copy(isDrawerClosed = !state.value.isDrawerClosed)
                    _state.emit(newState)
                }
                is MainEvent.SelectCategory -> {
                    val list = state
                        .value
                        .tasks
                        .map { it.copy(isFocused = it.categoryId == event.id) }
                    _state.emit(state.value.copy(tasks = list))
                }
                MainEvent.CreateNewCategory -> {

                }
                MainEvent.CreateNewTask -> {

                }
                is MainEvent.CompleteTask -> {
                    val oldCategory = state.value.categories
                        .first { it.id == event.task.categoryId }
                    val newCategory = if (event.task.isCompleted) {
                        oldCategory.copy(completedTasks = oldCategory.completedTasks - 1)
                    } else {
                        oldCategory.copy(completedTasks = oldCategory.completedTasks + 1)
                    }

                    val oldTask = state.value.tasks.first { it.id == event.task.id }
                    val newTask = oldTask.copy(isCompleted = !oldTask.isCompleted)

                    val newCategories = state.value.categories.map {
                        return@map if (it.id == event.task.categoryId) newCategory
                        else it
                    }

                    val newTasks = state.value.tasks.map {
                        return@map if (it.id == event.task.id) newTask
                        else it
                    }

                    _state.emit(state.value.copy(categories = newCategories, tasks = newTasks))
                }
            }
        }
    }
}