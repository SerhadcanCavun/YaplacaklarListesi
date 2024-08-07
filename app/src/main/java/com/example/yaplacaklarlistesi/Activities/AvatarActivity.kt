package com.example.yaplacaklarlistesi.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yaplacaklarlistesi.Adapter.AdapterAvatars
import com.example.yaplacaklarlistesi.DAO.UserDAO
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.interfaces.ImageStatusListener
import com.example.yaplacaklarlistesi.Model.Avatar
import com.example.yaplacaklarlistesi.R
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
            Avatar(R.drawable.spiderman),
            Avatar(R.drawable.varlik_1),
            Avatar(R.drawable.varlik_2),
            Avatar(R.drawable.varlik_3),
            Avatar(R.drawable.varlik_5),
            Avatar(R.drawable.varlik_6),
            Avatar(R.drawable.varlik_7),
            Avatar(R.drawable.varlik_8),
            Avatar(R.drawable.varlik_9),
            Avatar(R.drawable.varlik_10),
            Avatar(R.drawable.varlik_11),
            Avatar(R.drawable.varlik_12),
            Avatar(R.drawable.varlik_13),
            Avatar(R.drawable.varlik_14),
            Avatar(R.drawable.varlik_15),
            Avatar(R.drawable.varlik_16),
            Avatar(R.drawable.varlik_17),
            Avatar(R.drawable.varlik_18),
            Avatar(R.drawable.varlik_19),
            Avatar(R.drawable.varlik_20)
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
