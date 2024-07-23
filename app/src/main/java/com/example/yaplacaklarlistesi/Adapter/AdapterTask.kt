package com.example.yaplacaklarlistesi.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.R
import com.example.yaplacaklarlistesi.TaskItem

class AdapterTask(
    private var taskItems: List<TaskItem>,
) : RecyclerView.Adapter<AdapterTask.TaskViewHolder>() {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val radioButton : RadioButton = view.findViewById(R.id.radioButton)
        private val textView : TextView = view.findViewById(R.id.textView)

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
         TODO("Not yet implemented")
     }

     override fun getItemCount(): Int {
         TODO("Not yet implemented")
     }

     override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
         TODO("Not yet implemented")
     }


 }