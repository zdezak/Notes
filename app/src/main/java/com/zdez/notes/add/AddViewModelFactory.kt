package com.zdez.notes.add

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.notes.database.NotesDao
import java.lang.IllegalArgumentException

class AddViewModelFactory(
    private val database: NotesDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}