package com.example.yaplacaklarlistesi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.Adapter.AdapterTask

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private lateinit var taskItemTest: List<TaskItem>
    private lateinit var taskAdapter: AdapterTask
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        taskItemTest = listOf(
            TaskItem("a", true),
            TaskItem("b", true),
            TaskItem("c", true),
            TaskItem("d", true),
            TaskItem("a", true),
            TaskItem("b", true),
            TaskItem("c", true),
            TaskItem("d", true),
            TaskItem("a", true),
            TaskItem("b", true),
            TaskItem("c", true),
            TaskItem("d", true),
            TaskItem("a", true),
            TaskItem("b", true),
            TaskItem("c", true),
            TaskItem("d", true),

        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = AdapterTask(taskItemTest)
        recyclerView.adapter = taskAdapter
    }
}
