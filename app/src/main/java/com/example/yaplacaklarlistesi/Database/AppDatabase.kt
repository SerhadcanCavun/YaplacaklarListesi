package com.example.yaplacaklarlistesi.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.yaplacaklarlistesi.DAO.NotesDAO
import com.example.yaplacaklarlistesi.DAO.TaskDAO
import com.example.yaplacaklarlistesi.Model.Note
import com.example.yaplacaklarlistesi.Model.Task

@Database(entities = [Task::class, Note::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO
    abstract fun notesDao(): NotesDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DbConfig.ROOM_DATABASE
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)  // Add migrations here
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
            CREATE TABLE IF NOT EXISTS `notes_table` (
                `noteid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `note_title` TEXT,
                `note_text` TEXT
            )
            """.trimIndent()
                )
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Perform migration from version 2 to version 3
            }
        }
    }
}
