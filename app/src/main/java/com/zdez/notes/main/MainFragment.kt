package com.zdez.notes.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zdez.notes.adapter.NoteAdapter
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

        val binding = MainFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        val adapter = NoteAdapter()
        binding.noteList.adapter = adapter
        binding.lifecycleOwner = this

        viewModel.navigate.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate == true) {
                //this.findNavController().navigate()
                viewModel.navigateDone()
            }
        })
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })
        return binding.root
    }
}