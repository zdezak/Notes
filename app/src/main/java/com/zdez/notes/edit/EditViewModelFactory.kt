package com.zdez.notes.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.notes.database.NotesDao
import java.lang.IllegalArgumentException

class EditViewModelFactory(
    private val database: NotesDao
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditViewModel::class.java)){
            return EditViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}