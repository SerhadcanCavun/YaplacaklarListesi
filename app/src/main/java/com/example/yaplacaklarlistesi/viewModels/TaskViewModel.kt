package com.example.yaplacaklarlistesi.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.Repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _taskAddedEvent = MutableLiveData<Boolean>()
    val taskAddedEvent: LiveData<Boolean> = _taskAddedEvent

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getAllTasks()
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
            loadTasks()
        }
    }

    fun onTaskAdded() {
        _taskAddedEvent.value = true
    }

    fun resetTaskAddedEvent() {
        _taskAddedEvent.value = false
    }

    fun updateTaskStatus(task: Task) {
        viewModelScope.launch {
            repository.updateTaskStatus(task)
            //loadTasks()
        }

    }
}
