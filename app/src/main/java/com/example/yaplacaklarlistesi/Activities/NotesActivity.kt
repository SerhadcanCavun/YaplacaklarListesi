package com.example.yaplacaklarlistesi.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yaplacaklarlistesi.Adapter.AdapterNotes
import com.example.yaplacaklarlistesi.Database.InitDb
import com.example.yaplacaklarlistesi.Model.Note
import com.example.yaplacaklarlistesi.databinding.ActivityNotesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesActivity : AppCompatActivity() {

    private lateinit var noteItems: MutableList<Note>
    private lateinit var noteAdapter: AdapterNotes
    private lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteItems = mutableListOf()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        noteAdapter = AdapterNotes(noteItems)
        binding.recyclerView.adapter = noteAdapter

        loadNotes()

        binding.buttonImage.setOnClickListener {
            val noteText = binding.editNote.text.toString()
            if (noteText.isNotEmpty()) {
                addNote(noteText)
            }
        }

        binding.imageBack.setOnClickListener {
            backToLoginScreen()
        }
    }

    private fun loadNotes() {
        lifecycleScope.launch {
            val notes = withContext(Dispatchers.IO) {
                InitDb.appDatabase.notesDao().getAllNotes()
            }
            noteItems.addAll(notes)
            noteAdapter.notifyDataSetChanged()
        }
    }

    private fun addNote(noteText: String) {
        val note = Note(noteTitle = "Merhaba", noteText = "Dunya").apply {
            this.noteText = noteText
            this.noteTitle = noteText
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                InitDb.appDatabase.notesDao().insert(note)
            }
            noteItems.add(note)
            noteAdapter.notifyItemInserted(noteItems.size - 1)
            binding.editNote.text.clear()
        }
    }

    private fun backToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}