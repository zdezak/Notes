package com.zdez.notes.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zdez.notes.database.NotesDao

class MainViewModel(val database: NotesDao, application: Application) :
    AndroidViewModel(application) {

    private val _navigateToEditNote = MutableLiveData<Long?>()
    val navigateToEditNote: LiveData<Long?>
        get() = _navigateToEditNote

    val notes = database.getAllNotes()

    fun onNoteClicked(id: Long) {
        _navigateToEditNote.value = id
    }

    fun onNoteNavigated() {
        _navigateToEditNote.value = null
    }
}