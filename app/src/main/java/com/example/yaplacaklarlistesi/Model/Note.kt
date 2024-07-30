package com.example.yaplacaklarlistesi.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yaplacaklarlistesi.Database.DbConfig

@Entity(tableName =  DbConfig.NOTES_TABLE)
data class Note(
    @PrimaryKey(autoGenerate = true) val noteid: Int = 0,
    @ColumnInfo(name = "note_title") var noteTitle: String?,
    @ColumnInfo(name = "note_text") var noteText: String?
)
