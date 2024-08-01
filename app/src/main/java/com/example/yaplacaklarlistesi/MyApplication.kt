package com.example.yaplacaklarlistesi

import android.app.Application
import com.example.yaplacaklarlistesi.Database.AppDatabase
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Repositories.NotesRepository

class MyApplication : Application() {
    lateinit var database: AppDatabase
    lateinit var repository: NotesRepository
    override fun onCreate() {
        super.onCreate()
        InitDb.initialize(this)
        database = AppDatabase.getDatabase(this)
        repository = NotesRepository(database.notesDao())

    }
}