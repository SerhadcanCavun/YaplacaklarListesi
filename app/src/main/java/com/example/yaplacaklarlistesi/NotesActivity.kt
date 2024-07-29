package com.example.yaplacaklarlistesi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.Adapter.AdapterNotes
import com.example.yaplacaklarlistesi.Factory.NotesViewModelFactory
import com.example.yaplacaklarlistesi.ViewModels.NotesViewModel

class NotesActivity : AppCompatActivity() {

    private lateinit var noteAdapter: AdapterNotes

    private val notesViewModel: NotesViewModel by viewModels {
        NotesViewModelFactory((application as MyApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        noteAdapter = AdapterNotes(mutableListOf())

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        notesViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let { noteAdapter.updateNotes(it) }
        })
    }
}







