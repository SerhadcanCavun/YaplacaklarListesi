package com.example.yaplacaklarlistesi.Activities

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.UserState.currentUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddTaskDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        val editTextTask: EditText = view.findViewById(R.id.editTextTask)
        val buttonSave: Button = view.findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val taskText = editTextTask.text.toString()
            if (taskText.isNotEmpty()) {
                addTask(taskText)
            }
            dismiss()
        }

        return view
    }

    private fun addTask(taskText: String) {
        val currentTime = System.currentTimeMillis()
        val task = Task(task_user = currentUser!!.loginId, task_text = "df", task_boolean = false, task_date = currentTime, task_time = currentTime).apply {
            task_text = taskText
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                InitDb.appDatabase.taskDao().insert(task)
            }
        }
    }
}
