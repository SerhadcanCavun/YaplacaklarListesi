package com.example.yaplacaklarlistesi.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yaplacaklarlistesi.Database.DbConfig

@Entity(tableName = DbConfig.TASK_TABLE)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "task_user") var task_user: String?,
    @ColumnInfo(name = "task_text") var task_text: String?,
    @ColumnInfo(name = "task_boolean") var task_boolean: Boolean?,
    @ColumnInfo(name = "task_date") var task_date: String?


)