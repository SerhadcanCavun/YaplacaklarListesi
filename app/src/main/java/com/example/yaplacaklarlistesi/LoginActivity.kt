package com.example.yaplacaklarlistesi

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yaplacaklarlistesi.DAO.UserDao
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Model.User

class LoginActivity : AppCompatActivity() {

    lateinit var etLoginId: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLoginId = findViewById(R.id.etLoginId)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        userDao =InitDb.appDatabase.userDao()

        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val loginId = etLoginId.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (TextUtils.isEmpty(loginId) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter login credentials", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun register(registerId: String, registerPassword: String) {
        val user = User(loginId = registerId, password = registerPassword)

        userDao.insert(user)
    }
}
