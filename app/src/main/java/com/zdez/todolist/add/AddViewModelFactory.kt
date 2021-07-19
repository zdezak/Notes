package com.zdez.todolist.add

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zdez.todolist.database.NotesDao
import java.lang.IllegalArgumentException

class AddViewModelFactory(
    private val database: NotesDao,
    private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(database,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}