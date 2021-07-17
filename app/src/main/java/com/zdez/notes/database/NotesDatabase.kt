package com.zdez.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1,exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {
    abstract val notesDao: NotesDao
    companion object{
        @Volatile
        private var INSTANCE: NotesDatabase? = null
        fun getInstance(context: Context): NotesDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "notes"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}