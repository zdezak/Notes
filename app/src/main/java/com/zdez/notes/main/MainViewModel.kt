package com.zdez.notes.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zdez.notes.database.Note
import com.zdez.notes.database.NotesDao

class MainViewModel(val database: NotesDao, application: Application) :
    AndroidViewModel(application) {
    private val toNote = MutableLiveData<Note?>()
    private val notes = database.getAllNotes()
}