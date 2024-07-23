package com.example.yaplacaklarlistesi.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.R

 class AdapterTask(
    private val context: Context,
    private val isDone : Boolean,
    private var taskItems: List<CardView>,
) : RecyclerView.Adapter<AdapterTask.TaskViewHolder>() {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      private val taskItem : CardView = view.findViewById(R.id.taskItem)
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