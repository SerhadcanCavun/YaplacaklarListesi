package com.example.yaplacaklarlistesi.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yaplacaklarlistesi.Model.Task

@Dao
interface TaskDAO {
    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM task_table WHERE task_text = :text")
    fun getTaskById(text: String?): Task?

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): List<Task>
}
