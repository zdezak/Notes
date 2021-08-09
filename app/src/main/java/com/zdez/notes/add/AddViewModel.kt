package com.zdez.notes.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.notes.database.Note
import com.zdez.notes.database.NotesDao
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddViewModel(val database: NotesDao) : ViewModel() {
    val note = Note()
    private var viewModelJob = Job()
    private val _navigateToNoteList = MutableLiveData<Boolean>()
    val navigateToNoteList: LiveData<Boolean>
        get() = _navigateToNoteList

    init {
        _navigateToNoteList.value = false
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            database.insert(note)
        }

    }

    fun onClickButton() {
        _navigateToNoteList.value = true
    }

    fun doneNavigation() {
        _navigateToNoteList.value = false
    }
}