package com.example.yaplacaklarlistesi.interfaces

import com.example.yaplacaklarlistesi.Model.Task

interface OnTaskStatusChangedListener {
    fun onTaskStatusChanged(task: Task)

}