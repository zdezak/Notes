package com.zdez.todolist.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    val data = listOf<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = data.size
}