package com.example.yaplacaklarlistesi.Activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yaplacaklarlistesi.DAO.UserDAO
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.UserState.currentUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LoginActivity : AppCompatActivity() {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    lateinit var etLoginId: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnRgstr: Button
    lateinit var userDao: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLoginId = findViewById(R.id.etLoginId)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRgstr = findViewById(R.id.btnLgnRgstr)

        userDao =InitDb.appDatabase.userDao()

        btnLogin.setOnClickListener {
            login()
        }

        btnRgstr.setOnClickListener {
            registerScreen()
        }
    }

    private fun login() {
        val loginId = etLoginId.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (TextUtils.isEmpty(loginId) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter login credentials", Toast.LENGTH_SHORT).show()
            return
        }

        executorService.execute {
            val user = userDao.getUserById(loginId)

            runOnUiThread {
                if(user != null && password == user.password) {
                    currentUser = loginId
                    val intent = Intent(this@LoginActivity, ChooseActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid login credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun registerScreen() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
