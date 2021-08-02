package com.zdez.notes.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zdez.notes.adapter.NoteAdapter
import com.zdez.notes.adapter.NoteListener
import com.zdez.notes.database.NotesDatabase
import com.zdez.notes.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val dataSource = NotesDatabase.getInstance(application).notesDao
        val viewModelFactory = MainViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.addButton.setOnClickListener {
            this.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToAddFragment())
        }
        binding.viewModel = viewModel
        val adapter = NoteAdapter(NoteListener { noteId ->
            viewModel.onNoteClicked(noteId)
        })
        binding.noteList.adapter = adapter
        binding.lifecycleOwner = this

        viewModel.notes.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToEditNote.observe(viewLifecycleOwner, Observer { note ->
            note?.let { it ->
                this.findNavController()
                    .navigate(MainFragmentDirections.actionMainFragmentToEditFragment(it))
                viewModel.onNoteNavigated()
            }
        })
        return binding.root
    }
}