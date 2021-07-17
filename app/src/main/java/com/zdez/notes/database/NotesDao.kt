package com.zdez.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM notes  WHERE noteId = :key")
    suspend fun getNote(key: Long):Note?

    @Query("Delete FROM notes  WHERE noteId = :key")
    suspend fun deleteNotes(key: Long)

    @Query("SELECT * FROM notes ORDER BY noteId DESC LIMIT 1")
    suspend fun getToNote(): Note?

    @Query("SELECT * FROM notes ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM notes")
    suspend fun clear()
}