package com.example.yaplacaklarlistesi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yaplacaklarlistesi.DAO.TaskDAO
import com.example.yaplacaklarlistesi.Database.DbConfig
import com.example.yaplacaklarlistesi.Model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO?

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DbConfig.ROOM_DATABASE
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
