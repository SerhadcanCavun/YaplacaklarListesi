package com.example.yaplacaklarlistesi.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yaplacaklarlistesi.Model.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user_table WHERE login_id = :userId")
    fun getUserByLoginId(userId: String?): User?

    @get:Query("SELECT * FROM user_table")
    val allUsers: List<Any?>?
}