package com.example.yaplacaklarlistesi.Repository

import com.example.yaplacaklarlistesi.DAO.TaskDAO
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.UserState.currentUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(val taskDao: TaskDAO) {

    suspend fun getAllTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            taskDao.getTaskByUser(currentUser!!.loginId)
        }
    }

    suspend fun insert(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.insert(task)
        }
    }

    suspend fun updateTaskStatus(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.update(task)
        }
    }
}
