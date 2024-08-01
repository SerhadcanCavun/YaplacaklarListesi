package com.example.yaplacaklarlistesi.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yaplacaklarlistesi.Database.DbConfig

@Entity(tableName = DbConfig.USER_TABLE)
class User (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "login_id") var loginId: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "avatar") var avatar: String?
)