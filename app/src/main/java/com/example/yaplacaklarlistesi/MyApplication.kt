package com.example.yaplacaklarlistesi

import android.app.Application
import com.example.yaplacaklarlistesi.Database.AppDatabase
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Repository.TaskRepository

class MyApplication : Application() {
    lateinit var database: AppDatabase

    val taskRepository by lazy { TaskRepository(database.taskDao())}

    override fun onCreate() {
        super.onCreate()
        InitDb.initialize(this)
        database = AppDatabase.getDatabase(this)
    }
}