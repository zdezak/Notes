package com.zdez.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM Notes  WHERE noteId = :key")
    suspend fun getNote(key: Long):Note?

    @Query("Delete FROM Notes  WHERE noteId = :key")
    suspend fun deleteNotes(key: Long)

    @Query("SELECT * FROM Notes ORDER BY noteId DESC LIMIT 1")
    suspend fun getToNote(): Note?

    @Query("SELECT * FROM Notes ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM Notes")
    suspend fun clear()
}