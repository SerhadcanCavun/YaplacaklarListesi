package com.example.yaplacaklarlistesi.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yaplacaklarlistesi.Adapter.AdapterAvatars
import com.example.yaplacaklarlistesi.DAO.UserDAO
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Interface.ImageStatusListener
import com.example.yaplacaklarlistesi.Model.Avatar
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.UserState.currentUser
import com.example.yaplacaklarlistesi.databinding.ActivityAvatarBinding

class AvatarActivity : AppCompatActivity(), ImageStatusListener {
    private lateinit var binding: ActivityAvatarBinding
    lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvatarBinding.inflate(layoutInflater)
        binding.avatar.setImageResource(R.drawable.avatar3)
        setContentView(binding.root)
        initializeRecyclerView()

        userDAO = InitDb.appDatabase.userDao()

        binding.buttonAvatar.setOnClickListener{
            backRegisterScreen()
        }
    }

    private fun initializeRecyclerView() {
        binding.avatarRecyclerView.layoutManager = GridLayoutManager(this, 3)

        val avatars = listOf(
            Avatar(R.drawable.avatar1),
            Avatar(R.drawable.avatar2),
            Avatar(R.drawable.avatar3),
            Avatar(R.drawable.avatar4),
            Avatar(R.drawable.avatar5),
            Avatar(R.drawable.avatar6),
            Avatar(R.drawable.avatar7),
            Avatar(R.drawable.avatar8),
            Avatar(R.drawable.spiderman)
        )
        val adapterAvatars = AdapterAvatars(this, avatars, this)
        binding.avatarRecyclerView.adapter = adapterAvatars
    }

    override fun onImageChange(avatarResId: Int) {
        binding.avatar.setImageResource(avatarResId)
        println(avatarResId)
    }

    private fun backRegisterScreen() {
        val intent = Intent(this@AvatarActivity, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
