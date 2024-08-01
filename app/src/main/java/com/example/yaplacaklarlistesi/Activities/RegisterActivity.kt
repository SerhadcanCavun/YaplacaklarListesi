package com.example.yaplacaklarlistesi.Activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yaplacaklarlistesi.DAO.UserDAO
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Model.User
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.UserInformationState
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.example.yaplacaklarlistesi.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private lateinit var binding: ActivityRegisterBinding
    lateinit var userDao: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val avatarname = this.resources.getIdentifier(UserInformationState.avatarName, "drawable", this.packageName)
        binding.imageRegisterLogo.setImageResource(avatarname)
        setContentView(binding.root)

        userDao =InitDb.appDatabase.userDao()

        binding.btnRegister.setOnClickListener {
            register()
        }

        binding.iconBack.setOnClickListener {
            exit()
        }

        binding.btnRgstrLgn.setOnClickListener {
            exit()
        }

        binding.imageRegisterLogo.setOnClickListener {
            selectAvatar()
        }
    }

    private fun register() {
        val registerId = binding.etRegisterId.text.toString().trim()
        val registerPassword = binding.etRegisterPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        if(registerId == "" || registerPassword == ""){
            showDialog("Enter validated values")
            return
        }

        if(registerPassword != confirmPassword){
            showDialog("Passwords couldn't match")
            return
            }

        val user = User(loginId = registerId, password = registerPassword, avatar = UserInformationState.avatarName)
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
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        val alert = dialogBuilder.create()
        alert.show()
    }

    private fun selectAvatar(){
        val intent = Intent(this@RegisterActivity, AvatarActivity::class.java)
        startActivity(intent)
        finish()
    }
}