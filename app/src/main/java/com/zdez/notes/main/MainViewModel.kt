package com.zdez.todolist.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zdez.todolist.database.Note
import com.zdez.todolist.database.NotesDao

class MainViewModel(val database: NotesDao, application: Application) :
    AndroidViewModel(application) {
    private val toNote = MutableLiveData<Note?>()
    private val notes = database.getAllNotes()
}