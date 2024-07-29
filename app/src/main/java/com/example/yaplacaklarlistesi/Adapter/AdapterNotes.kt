package com.example.yaplacaklarlistesi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.Model.Note
import com.example.yaplacaklarlistesi.R

class AdapterNotes(
    private var noteItems: MutableList<Note>
) : RecyclerView.Adapter<AdapterNotes.NotesViewHolder>() {

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.text_message_title)
        val textViewBody: TextView = view.findViewById(R.id.text_message_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_note_design, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val noteItem = noteItems[position]
        holder.titleTextView.text = noteItem.noteTitle
        holder.textViewBody.text = noteItem.noteText
    }

    override fun getItemCount(): Int {
        return noteItems.size
    }

    fun updateNotes(notes: List<Note>) {
        noteItems.clear()
        noteItems.addAll(notes)
        notifyDataSetChanged()
    }
}
