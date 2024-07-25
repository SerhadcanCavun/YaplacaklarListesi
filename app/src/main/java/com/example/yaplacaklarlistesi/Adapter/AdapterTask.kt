//package com.example.yaplacaklarlistesi.Adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.RadioButton
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.yaplacaklarlistesi.R
//import com.example.yaplacaklarlistesi.TaskItem
//
//class AdapterTask(
//    private var taskItems: List<TaskItem>
//) : RecyclerView.Adapter<AdapterTask.TaskViewHolder>() {
//
//    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val radioButton : RadioButton = view.findViewById(R.id.radioButton)
//        val textView : TextView = view.findViewById(R.id.textView)
//
//    }
//
//     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
//         val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
//         return TaskViewHolder(view)
//     }
//
//     override fun getItemCount(): Int {
//         return taskItems.count()
//     }
//
//     override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
//         val taskItem = taskItems[position]
//         holder.textView.text = taskItem.task_text
//         holder.radioButton.isChecked = taskItem.isDone
//     }
//
//
// }