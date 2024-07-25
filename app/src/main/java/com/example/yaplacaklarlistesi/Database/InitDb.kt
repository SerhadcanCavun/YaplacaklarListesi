package com.example.yaplacaklarlistesi.Database

import android.content.Context

object InitDb {
    lateinit var appDatabase: AppDatabase

    fun initialize(context: Context) {
        appDatabase = AppDatabase.getDatabase(context)
    }
}