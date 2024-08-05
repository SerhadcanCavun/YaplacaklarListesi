package com.example.yaplacaklarlistesi.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yaplacaklarlistesi.Model.Note

@Dao
interface NotesDAO {
    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM notes_table WHERE note_title = :title")
    fun getNoteByTitle(title: String?): Note?

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): List<Note>
}
