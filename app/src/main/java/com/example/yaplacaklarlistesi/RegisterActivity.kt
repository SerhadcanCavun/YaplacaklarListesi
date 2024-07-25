package com.example.yaplacaklarlistesi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.yaplacaklarlistesi.DAO.UserDao
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Model.User

class RegisterActivity : AppCompatActivity() {

    lateinit var registerId: EditText
    lateinit var registerPassword: EditText
    lateinit var btnRegister: Button
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerId = findViewById(R.id.etRegisterId)
        registerPassword = findViewById(R.id.etRegisterPassword)
        btnRegister = findViewById(R.id.btnRegister)

        userDao =InitDb.appDatabase.userDao()

        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val registerId = registerId.text.toString().trim()
        val registerPassword = registerPassword.text.toString().trim()

        val user = User(loginId = registerId, password = registerPassword)

        userDao.insert(user)
    }
}