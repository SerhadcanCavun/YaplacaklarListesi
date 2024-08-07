package com.example.yaplacaklarlistesi.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yaplacaklarlistesi.Adapter.AdapterTask
import com.example.yaplacaklarlistesi.Factory.TaskViewModelFactory
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.MyApplication
import com.example.yaplacaklarlistesi.viewModels.TaskViewModel
import com.example.yaplacaklarlistesi.databinding.ActivityTaskBinding
import com.example.yaplacaklarlistesi.interfaces.OnTaskStatusChangedListener

class TaskActivity : AppCompatActivity(), OnTaskStatusChangedListener {
    private val taskItems: MutableList<Task> = mutableListOf()
    private lateinit var binding: ActivityTaskBinding
    private lateinit var taskAdapter: AdapterTask
    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application: MyApplication = application as MyApplication
        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(application.taskRepository)).get(TaskViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = AdapterTask(this,taskItems)
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
            }
        })

        taskViewModel.taskAddedEvent.observe(this, Observer { event ->
            if (event) {
                taskViewModel.resetTaskAddedEvent()
                taskAdapter.notifyItemInserted(taskItems.size)
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

    override fun onTaskStatusChanged(task: Task) {
        taskViewModel.updateTaskStatus(task)
    }
}
