package com.example.yaplacaklarlistesi.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.yaplacaklarlistesi.NotesActivity
import com.example.yaplacaklarlistesi.R

class ChooseActivity: AppCompatActivity() {

    lateinit var button_note : Button
    lateinit var button_task : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        button_task = findViewById(R.id.task_button)
        button_note = findViewById(R.id.note_button)

        button_task.setOnClickListener {
            openTaskActivity()
        }

        button_note.setOnClickListener {
            openNoteActivity()
        }
    }

    private fun openNoteActivity() {
        val intent = Intent(this@ChooseActivity, NotesActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun openTaskActivity() {
        val intent = Intent(this@ChooseActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
