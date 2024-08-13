package com.example.yaplacaklarlistesi.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.Repository.TaskRepository
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _taskAddedEvent = MutableLiveData<Boolean>()
    val taskAddedEvent: LiveData<Boolean> = _taskAddedEvent

    private val _taskDeletedEvent = MutableLiveData<Boolean>()
    val taskDeletedEvent: LiveData<Boolean> = _taskDeletedEvent

    private val _taskUpdatedEvent = MutableLiveData<Boolean>()
    val taskUpdatedEvent: LiveData<Boolean> = _taskUpdatedEvent

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            val deneme =  repository.getAllTasks()

            _tasks.value = deneme
        }
    }

    fun onTaskAdded() {
        _taskAddedEvent.value = true
    }

    fun resetTaskAddedEvent() {
        _taskAddedEvent.value = false
    }

    fun onTaskDeleted() {
        _taskDeletedEvent.value = true
    }

    fun resetTaskDeletedEvent() {
        _taskDeletedEvent.value = false
    }

    fun onTaskUpdated() {
        _taskUpdatedEvent.value = true
    }

    fun resetTaskUpdatedEvent() {
        _taskUpdatedEvent.value = false
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
            loadTasks()
        }
    }

    fun updateTaskStatus(task: Task) {
        viewModelScope.launch {
            repository.updateTaskStatus(task)
        }
    }

    fun deleteTaskStatus(task: Task) {
        viewModelScope.launch {
            repository.deleteTaskStatus(task)
            loadTasks()
        }
    }
}
