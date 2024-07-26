package com.example.yaplacaklarlistesi.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.Model.User

@Dao
interface UserDAO {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user_table WHERE login_id = :text")
    fun getUserById(text: String?): User?
}