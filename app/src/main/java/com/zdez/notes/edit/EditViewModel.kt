package com.zdez.notes.edit

import androidx.lifecycle.*
import com.zdez.notes.database.Note
import com.zdez.notes.database.NotesDao
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EditViewModel(dataSource: NotesDao, noteId: Long = 0L) : ViewModel() {
    val database = dataSource

    private var viewModelJob = Job()
    private val _navigateToMainOnSave = MutableLiveData<Boolean>()
    val navigateToMainOnSave: LiveData<Boolean>
        get() = _navigateToMainOnSave

    private val _navigateToMainOnDelete = MutableLiveData<Boolean>()
    val navigateToMainOnDelete: LiveData<Boolean>
        get() = _navigateToMainOnDelete
    private val note = database.getNoteWithKey(noteId)


    init {
        _navigateToMainOnSave.value = false
        _navigateToMainOnDelete.value = false
    }

    fun getNote() = note


    fun saveEditNote(note: Note) {
        viewModelScope.launch {
            database.update(note)
        }
    }

    fun onClickSaveButton() {
        _navigateToMainOnSave.value = true
    }

    fun navigateCompleted() {
        _navigateToMainOnSave.value = false
        _navigateToMainOnDelete.value = false
    }

    fun onClickDeleteButton(){
        _navigateToMainOnDelete.value = true
    }

    fun deleteNote(id: Long){
        viewModelScope.launch {
            database.deleteNotes(id)
        }
    }
}