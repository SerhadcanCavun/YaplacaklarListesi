package com.example.yaplacaklarlistesi

import android.app.Application
import com.example.yaplacaklarlistesi.Database.InitDb

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize the database
        InitDb.initialize(this)
    }
}