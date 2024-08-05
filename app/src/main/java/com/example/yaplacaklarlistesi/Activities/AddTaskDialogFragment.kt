package com.example.yaplacaklarlistesi.Activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddTaskDialogFragment : DialogFragment() {
    private var date: String? = null
    private var time: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        val editTextTask: EditText = view.findViewById(R.id.editTextTask)
        val buttonSave: Button = view.findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val taskText = editTextTask.text.toString()
            showDatePicker(taskText)
        }
        return view
    }

    private fun addTask(taskText: String) {
       val task = Task(task_user = currentUser!!.loginId, task_text = "df", task_boolean = false, task_date = "aa").apply {
           task_text = taskText
           task_date = "$date $time"
       }

       lifecycleScope.launch {
           withContext(Dispatchers.IO) {
               InitDb.appDatabase.taskDao().insert(task)
           }
       }
    }

    private fun showDatePicker(taskText: String) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay -> date = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            showTimePicker(taskText)
        }, year, month, day)



        datePickerDialog.show()
    }

    private fun showTimePicker(taskText: String) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute -> time = "$selectedHour:$selectedMinute"
            addTask(taskText)
        }, hour, minute, true)

        timePickerDialog.show()
    }

}
