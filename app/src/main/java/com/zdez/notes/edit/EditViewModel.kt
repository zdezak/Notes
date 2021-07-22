package com.zdez.notes.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdez.notes.database.Note
import com.zdez.notes.database.NotesDao
import kotlinx.coroutines.launch

class EditViewModel(dataSource: NotesDao) : ViewModel() {
    val database = dataSource
    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain

    init {
        _navigateToMain.value = false
    }

    fun saveEditNote(note: Note){
        viewModelScope.launch {
            database.update(note)
        }
    }

    fun onClickNavigateButton(){
        _navigateToMain.value = true
    }

    fun navigateCompleted(){
        _navigateToMain.value = false
    }
}