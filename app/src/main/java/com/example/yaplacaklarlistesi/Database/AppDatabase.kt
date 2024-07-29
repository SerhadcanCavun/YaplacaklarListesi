package com.example.yaplacaklarlistesi.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.yaplacaklarlistesi.DAO.NotesDAO
import com.example.yaplacaklarlistesi.DAO.TaskDAO
import com.example.yaplacaklarlistesi.DAO.UserDAO
import com.example.yaplacaklarlistesi.Model.Note
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.Model.User

@Database(entities = [Task::class, User::class, Note::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO
    abstract fun userDao(): UserDAO
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
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Migration from version 1 to 2: adding a new column to task_table
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE task_table ADD COLUMN task_user TEXT")
            }
        }

        // Migration from version 2 to 3: creating a new notes_table
        private val MIGRATION_2_3 = object : Migration(2, 3) {
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

        // Placeholder for future migration from version 3 to 4
        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Add migration logic here when upgrading from version 3 to 4
            }
        }
    }
}
