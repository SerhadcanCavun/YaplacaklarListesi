package com.example.yaplacaklarlistesi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.Model.Task
import com.example.yaplacaklarlistesi.R

class AdapterNotes(
    private var noteItems: MutableList<Task>
) : RecyclerView.Adapter<AdapterNotes.NotesViewHolder>() {

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val radioButton: RadioButton = view.findViewById(R.id.radioButton)
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNotes.NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val noteItem = noteItems[position]
        holder.textView.text = noteItem.task_text
        holder.radioButton.isChecked = noteItem.task_boolean ?: false
    }

    override fun getItemCount(): Int {
        return noteItems.size
    }


}