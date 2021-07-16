package com.zdez.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long = 0L,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "notes_text")
    val notesText: String = ""
)
