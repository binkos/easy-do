package com.proxer.easydo.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _mainState: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> = _mainState.asStateFlow()

    private val _createTaskState: MutableStateFlow<CreateTaskState> =
        MutableStateFlow(CreateTaskState())
    val createTaskState: StateFlow<CreateTaskState> = _createTaskState.asStateFlow()

    fun sendMainEvent(event: MainEvent) {
        viewModelScope.launch {
            when (event) {
                MainEvent.ClickOnDrawer -> {
                    val newState =
                        mainState.value.copy(isDrawerClosed = !mainState.value.isDrawerClosed)
                    _mainState.emit(newState)
                }
                is MainEvent.SelectCategory -> {
                    val list = mainState
                        .value
                        .taskModels
                        .map { it.copy(isFocused = it.categoryId == event.id) }
                    _mainState.emit(mainState.value.copy(taskModels = list))
                }
                MainEvent.CreateNewCategory -> {

                }
                MainEvent.CreateNewTask -> {

                }
                is MainEvent.CompleteTask -> {
                    val oldCategory = mainState.value.categories
                        .first { it.id == event.taskModel.categoryId }
                    val newCategory = if (event.taskModel.isCompleted) {
                        oldCategory.copy(completedTasks = oldCategory.completedTasks - 1)
                    } else {
                        oldCategory.copy(completedTasks = oldCategory.completedTasks + 1)
                    }

                    val oldTask = mainState.value.taskModels.first { it.id == event.taskModel.id }
                    val newTask = oldTask.copy(isCompleted = !oldTask.isCompleted)

                    val newCategories = mainState.value.categories.map {
                        return@map if (it.id == event.taskModel.categoryId) newCategory
                        else it
                    }

                    val newTasks = mainState.value.taskModels.map {
                        return@map if (it.id == event.taskModel.id) newTask
                        else it
                    }

                    _mainState.emit(
                        mainState.value.copy(
                            categories = newCategories,
                            taskModels = newTasks
                        )
                    )
                }
            }
        }
    }

    fun sendCreateTaskEvent(event: CreateTaskEvent) {
        viewModelScope.launch {
            when (event) {
                is CreateTaskEvent.CreateTask -> {
                    val categoryId =
                        mainState.value.categories.indexOfFirst { it.id == event.taskModel.categoryId }

                    val newCategories = mainState.value.categories.toMutableList()
                        .apply {
                            val oldCategory = mainState.value.categories[categoryId]
                            set(categoryId, oldCategory.copy(allTasks = oldCategory.allTasks + 1))
                        }

                    mainState
                        .value
                        .taskModels
                        .toMutableList()
                        .apply { add(event.taskModel.copy(id = size)) }
                        .also {
                            _mainState.emit(
                                mainState.value.copy(
                                    categories = newCategories,
                                    taskModels = it
                                )
                            )
                        }
                }
                is CreateTaskEvent.SelectCategory -> {
                    createTaskState
                        .value
                        .categories
                        .map {
                            return@map when (it.id == event.category.id) {
                                true -> it.copy(isSelected = !it.isSelected)
                                false ->
                                    if (it.isSelected) it.copy(isSelected = false)
                                    else it
                            }
                        }
                        .also {
                            _createTaskState.emit(_createTaskState.value.copy(categories = it))
                        }
                }
            }
        }
    }
}