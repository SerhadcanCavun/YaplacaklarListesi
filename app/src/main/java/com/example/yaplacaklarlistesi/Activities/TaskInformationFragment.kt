package com.example.yaplacaklarlistesi.Activities

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.viewModels.TaskViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class TaskInformationFragment(var task: Task) : DialogFragment() {

    private val taskViewModel: TaskViewModel by activityViewModels()

    private lateinit var taskInformation: EditText
    private lateinit var dateInformation: TextView
    private lateinit var saveButton1: TextView
    private lateinit var editButton1: ImageView

    private var date: String? = null
    private var time: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_information, container, false)

        taskInformation = view.findViewById(R.id.task_information)
        dateInformation = view.findViewById(R.id.date_information)
        saveButton1 = view.findViewById(R.id.save_button1)
        editButton1 = view.findViewById(R.id.edit_button1)

        taskDetails(task)

        saveButton1.setOnClickListener {
            editTaskText(task)
            showDialog("Task updated successfully")
        }

        editButton1.setOnClickListener {
            showDatePicker()
        }

        return view
    }


    private fun taskDetails(task: Task) {
        taskInformation.hint = task.task_text
        dateInformation.text = task.task_date
    }

    private fun editTaskText(task: Task) {
        task.task_text = taskInformation.text.toString()

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                taskViewModel.updateTaskStatus(task)
            }
            withContext(Dispatchers.Main) {
                taskViewModel.onTaskUpdated()
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            date = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            showTimePicker()
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            time = "$selectedHour:$selectedMinute"
            updateTaskDate(task)
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun updateTaskDate(task: Task) {
        task.task_date = "$date $time"

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                taskViewModel.updateTaskStatus(task)
            }
        }
        taskViewModel.onTaskUpdated()
        showDialog("Task date updated successfully")
    }

    private fun showDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        val alert = dialogBuilder.create()
        alert.show()
    }
}