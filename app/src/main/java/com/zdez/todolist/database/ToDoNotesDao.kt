package com.zdez.todolist.database

import androidx.room.*

@Dao
interface ToDoNotesDao {
    @Insert
    fun insert(todoNotes: TodoNotes)

    @Update
    fun update(todoNotes: TodoNotes)

    @Query("SELECT * FROM ToDoNotes  WHERE noteId = :key")
    fun getToDoNotes(key: Long):TodoNotes?

    @Query("Delete FROM ToDoNotes  WHERE noteId = :key")
    fun deleteNotes(key: Long)
}