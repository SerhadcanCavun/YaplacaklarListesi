package com.example.yaplacaklarlistesi

import android.os.Bundle
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var taskItems: MutableList<Task>
    lateinit var taskAdapter: AdapterTask
    lateinit var recyclerView: RecyclerView
    lateinit var imageView: ImageView
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         // Ensure the database is initialized

        editText = findViewById(R.id.edit_text_message)
        imageView = findViewById(R.id.button_image)

        taskItems = mutableListOf()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = AdapterTask(taskItems)
        recyclerView.adapter = taskAdapter

        loadTasks()

        imageView.setOnClickListener {
            val taskText = editText.text.toString()
            if (taskText.isNotEmpty()) {
                addTask(taskText)
            }
        }
    }

    private fun loadTasks() {
        lifecycleScope.launch {
            val tasks = withContext(Dispatchers.IO) {
                InitDb.appDatabase.taskDao().getAllTasks()
            }
            taskItems.addAll(tasks)
            taskAdapter.notifyDataSetChanged()
        }
    }

    private fun addTask(taskText: String) {
        val task = Task(task_text = "df", task_boolean = true).apply {
            task_text = taskText
            task_boolean = false
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                InitDb.appDatabase.taskDao().insert(task)
            }
            taskItems.add(task)
            taskAdapter.notifyItemInserted(taskItems.size - 1)
            editText.text.clear()
        }
    }
}