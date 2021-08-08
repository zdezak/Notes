package com.zdez.notes.edit

import androidx.lifecycle.*
import com.zdez.notes.database.Note
import com.zdez.notes.database.NotesDao
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EditViewModel(dataSource: NotesDao, noteId: Long = 0L) : ViewModel() {
    val database = dataSource

    private var viewModelJob = Job()
    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain

    private val note = database.getNoteWithKey(noteId)


    init {
        _navigateToMain.value = false
    }

    fun getNote() = note


    fun saveEditNote(note: Note) {
        viewModelScope.launch {
            database.update(note)
        }
    }

    fun onClickNavigateButton() {
        _navigateToMain.value = true
    }

    fun navigateCompleted() {
        _navigateToMain.value = false
    }
}