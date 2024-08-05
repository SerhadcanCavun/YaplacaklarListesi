package com.example.yaplacaklarlistesi

import android.app.Application
import com.example.yaplacaklarlistesi.Database.AppDatabase
import com.example.yaplacaklarlistesi.Database.InitDb

class MyApplication : Application() {
    lateinit var database: AppDatabase
    override fun onCreate() {
        super.onCreate()
        InitDb.initialize(this)
        database = AppDatabase.getDatabase(this)
    }
}