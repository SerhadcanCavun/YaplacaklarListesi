package com.example.yaplacaklarlistesi.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yaplacaklarlistesi.Adapter.AdapterTask
import com.example.yaplacaklarlistesi.Factory.TaskViewModelFactory
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.MyApplication
import com.example.yaplacaklarlistesi.UserState.currentUser
import com.example.yaplacaklarlistesi.viewModels.TaskViewModel
import com.example.yaplacaklarlistesi.databinding.ActivityTaskBinding
import com.example.yaplacaklarlistesi.interfaces.OnTaskSelectedListener
import com.example.yaplacaklarlistesi.interfaces.OnTaskStatusChangedListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskActivity : AppCompatActivity(), OnTaskStatusChangedListener, OnTaskSelectedListener {
    private val taskItems: MutableList<Task> = mutableListOf()
    private lateinit var binding: ActivityTaskBinding
    private lateinit var taskAdapter: AdapterTask
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userImage.setImageResource(resources.getIdentifier(currentUser!!.avatar, "drawable", packageName))

        binding.userId.text = currentUser!!.loginId

        val application: MyApplication = application as MyApplication
        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(application.taskRepository))[TaskViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = AdapterTask(this,this, taskItems)
        binding.recyclerView.adapter = taskAdapter

        binding.buttonImage.setOnClickListener {
            openAddTaskDialog()
        }

        binding.imageBack.setOnClickListener {
            backToLoginScreen()
        }

        taskViewModel.tasks.observe(this, Observer { tasks ->
            tasks.let {
                taskItems.clear()
                taskItems.addAll(it)
                taskAdapter.notifyDataSetChanged()
            }
        })

        taskViewModel.taskAddedEvent.observe(this, Observer { event ->
            if (event) {
                taskViewModel.resetTaskAddedEvent()
                taskAdapter.notifyItemInserted(taskItems.size)
            }
        })

        taskViewModel.taskDeletedEvent.observe(this, Observer { event ->
            if (event) {
                taskViewModel.resetTaskDeletedEvent()
                taskAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun openAddTaskDialog() {
        val addTaskDialog = AddTaskFragment()
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

    override fun onTaskDelete(task: Task) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                taskViewModel.deleteTaskStatus(task)
            }
            withContext(Dispatchers.Main) {
                taskViewModel.onTaskDeleted()
            }
        }
    }

    override fun onTaskSelected(task: Task) {
        val taskInformationDialog = TaskInformationFragment(task)
        taskInformationDialog.show(supportFragmentManager, "TaskInformationDialog")
    }
}