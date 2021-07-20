package com.zdez.notes.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zdez.notes.database.NotesDao

class MainViewModel(dataSource: NotesDao, application: Application) : ViewModel() {

    val database = dataSource
    val notes = database.getAllNotes()
    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate
    init {
        _navigate.value = false
    }
    fun navigateDone(){
        _navigate.value = false
    }
    fun onClickAddButton(){
        _navigate.value = true
    }
}