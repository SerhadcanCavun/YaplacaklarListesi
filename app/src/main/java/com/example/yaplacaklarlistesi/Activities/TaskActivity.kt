package com.example.yaplacaklarlistesi.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yaplacaklarlistesi.Adapter.AdapterTask
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.ViewModel.TaskViewModel
import com.example.yaplacaklarlistesi.Factory.TaskViewModelFactory
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.MyApplication
import com.example.yaplacaklarlistesi.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    lateinit var taskItems: MutableList<Task>
    lateinit var taskAdapter: AdapterTask

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as MyApplication).taskRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        binding = ActivityTaskBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = AdapterTask(mutableListOf())
        binding.recyclerView.adapter = taskAdapter

        binding.buttonImage.setOnClickListener {
            openAddTaskDialog()
        }

        binding.imageBack.setOnClickListener {
            backToLoginScreen()
        }
        taskViewModel.tasks.observe(this, Observer { tasks ->
            tasks?.let {
                taskItems.clear()
                taskItems.addAll(it)
                taskAdapter.notifyDataSetChanged()
            }
        })

        taskViewModel.taskAddedEvent.observe(this, Observer { event ->
            if (event) {
                taskViewModel.loadTasks()
                taskViewModel.resetTaskAddedEvent()
            }
        })
    }

    private fun openAddTaskDialog() {
        val addTaskDialog = AddTaskDialogFragment()
        addTaskDialog.show(supportFragmentManager, "AddTaskDialog")
    }

    private fun backToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
