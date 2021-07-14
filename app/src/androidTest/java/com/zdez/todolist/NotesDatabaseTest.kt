package com.zdez.todolist

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.zdez.todolist.database.NotesDatabase
import com.zdez.todolist.database.ToDoNotesDao
import com.zdez.todolist.database.TodoNotes
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class NotesDatabaseTest {

    private lateinit var noteDao: ToDoNotesDao
    private lateinit var db: NotesDatabase
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, NotesDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        noteDao = db.toDoNotesDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertAndGetNight() {
        val note = TodoNotes()
        noteDao.insert(note)
        val tonote = noteDao.getTonotes()
        assertEquals(tonote?.title, "")
    }
}
