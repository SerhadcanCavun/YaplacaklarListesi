package com.example.yaplacaklarlistesi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.interfaces.OnTaskSelectedListener
import com.example.yaplacaklarlistesi.interfaces.OnTaskStatusChangedListener
import kotlinx.coroutines.withContext

class AdapterTask(
    private val onTaskStatusChangedListener: OnTaskStatusChangedListener,
    private val onTaskSelectedListener: OnTaskSelectedListener,
    private var taskItems: MutableList<Task>
) : RecyclerView.Adapter<AdapterTask.TaskViewHolder>() {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val radioButton: RadioButton = view.findViewById(R.id.radioButton)
        val textView: TextView = view.findViewById(R.id.textView)
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val deleteButton: ImageView = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskItems.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val taskItem = taskItems[position]
        holder.textView.text = taskItem.task_text
        holder.dateTextView.text = taskItem.task_date
        holder.radioButton.isChecked = taskItem.task_boolean ?: false

        holder.radioButton.setOnClickListener {
            onTaskStatusChangedListener.onTaskStatusChanged(taskItem)
        }

        holder.deleteButton.setOnClickListener {
            onTaskStatusChangedListener.onTaskDelete(taskItem)
        }

        holder.textView.setOnClickListener {
            onTaskSelectedListener.onTaskSelected(taskItem)
        }
    }
}
