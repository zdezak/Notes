package com.zdez.notes.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zdez.notes.database.Note
import com.zdez.notes.database.NotesDatabase
import com.zdez.notes.databinding.EditFragmentBinding
import com.zdez.notes.main.MainFragment

class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = EditFragmentBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = NotesDatabase.getInstance(application).notesDao
        val viewModelFactory = EditViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val note = Note()
                note.title = binding.EditTitle.toString()
                note.notesText = binding.EditTitle.toString()
                viewModel.saveEditNote(note)
                this.findNavController().navigate(EditFragmentDirections.actionEditFragmentToMainFragment())
                viewModel.navigateCompleted()
            }
        })

        return binding.root
    }


}