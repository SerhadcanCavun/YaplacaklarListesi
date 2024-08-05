package com.example.yaplacaklarlistesi.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.Adapter.AdapterTask
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.UserState.currentUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskActivity : AppCompatActivity() {
    lateinit var taskItems: MutableList<Task>
    lateinit var taskAdapter: AdapterTask
    lateinit var recyclerView: RecyclerView
    lateinit var imageView: ImageView
    lateinit var imageBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_todo)

        imageView = findViewById(R.id.button_image)
        imageBack = findViewById(R.id.imageBack)

        taskItems = mutableListOf()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = AdapterTask(taskItems)
        recyclerView.adapter = taskAdapter

        loadTasks()

        imageView.setOnClickListener {
            openAddTaskDialog()
        }

        imageBack.setOnClickListener {
            backToLoginScreen()
        }
    }

    private fun openAddTaskDialog() {
        val addTaskDialog = AddTaskDialogFragment()
        addTaskDialog.show(supportFragmentManager, "AddTaskDialog")
    }

    private fun loadTasks() {
        lifecycleScope.launch {
            val tasks = withContext(Dispatchers.IO) {
                InitDb.appDatabase.taskDao().getTaskById(currentUser!!.loginId)
            }
            taskItems.addAll(tasks)
            taskAdapter.notifyItemInserted(tasks.size)
        }
    }

    private fun backToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}