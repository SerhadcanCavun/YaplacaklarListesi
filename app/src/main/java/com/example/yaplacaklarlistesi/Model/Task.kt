package com.example.yaplacaklarlistesi.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.yaplacaklarlistesi.Database.DbConfig

@Entity(tableName = DbConfig.TASK_TABLE)
class Task {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "task_text")
    var task_text: String?= null

    @ColumnInfo(name = "task_boolean")
    var task_boolean: Boolean?= false
}
