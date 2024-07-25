package com.example.yaplacaklarlistesi.Database

import android.app.Application
import com.example.yaplacaklarlistesi.database.AppDatabase

class InitDb : Application() {
    companion object {
        lateinit var appDatabase: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = AppDatabase.getDatabase(this)
    }
}