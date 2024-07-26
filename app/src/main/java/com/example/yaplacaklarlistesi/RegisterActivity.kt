package com.example.yaplacaklarlistesi

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.yaplacaklarlistesi.DAO.UserDAO
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Model.User
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RegisterActivity : AppCompatActivity() {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    lateinit var registerId: EditText
    lateinit var registerPassword: EditText
    lateinit var btnRegister: Button
    lateinit var btnExit: Button
    lateinit var userDao: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerId = findViewById(R.id.etRegisterId)
        registerPassword = findViewById(R.id.etRegisterPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnExit = findViewById(R.id.btnExit)

        userDao =InitDb.appDatabase.userDao()

        btnRegister.setOnClickListener {
            register()
        }

        btnExit.setOnClickListener {
            exit()
        }
    }

    private fun register() {
        val registerId = registerId.text.toString().trim()
        val registerPassword = registerPassword.text.toString().trim()

        if(registerId == "" || registerPassword == ""){
            showDialog("Enter validated values")
            return
        }

        val user = User(loginId = registerId, password = registerPassword)
        executorService.execute {
            userDao.insert(user)

            runOnUiThread {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun exit() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Tamam") { dialog, _ -> dialog.dismiss() }
        val alert = dialogBuilder.create()
        alert.show()
    }
}