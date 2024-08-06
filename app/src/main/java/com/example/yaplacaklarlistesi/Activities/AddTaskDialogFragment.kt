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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.UserState.currentUser
import com.example.yaplacaklarlistesi.ViewModel.TaskViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class AddTaskDialogFragment : DialogFragment() {

    private val taskViewModel: TaskViewModel by activityViewModels()

    private var date: String? = null
    private var time: String? = null
    private var globalTaskText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        val editTextTask: EditText = view.findViewById(R.id.editTextTask)
        val buttonSave: Button = view.findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            globalTaskText = editTextTask.text.toString()
            showDatePicker()
        }
        return view
    }

    private fun addTask() {
        val task = Task(
            task_user = currentUser!!.loginId,
            task_text = globalTaskText ?: "",
            task_boolean = false,
            task_date = "$date $time"
        )

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                taskViewModel.addTask(task)
            }
            withContext(Dispatchers.Main) {
                taskViewModel.onTaskAdded()
                dismiss()
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
            addTask()
        }, hour, minute, true)

        timePickerDialog.show()
    }
}
