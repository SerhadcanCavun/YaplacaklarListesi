package com.example.yaplacaklarlistesi.Repository

import com.example.yaplacaklarlistesi.DAO.TaskDAO
import com.example.yaplacaklarlistesi.Model.Task

class TaskRepository(private val taskDao: TaskDAO) {

    suspend fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }
}
