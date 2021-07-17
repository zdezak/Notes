package com.zdez.notes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "notes_text")
    val notesText: String = ""
)
