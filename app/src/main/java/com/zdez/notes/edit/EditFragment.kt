package com.zdez.notes.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zdez.notes.database.NotesDatabase
import com.zdez.notes.databinding.EditFragmentBinding

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
        val arguments = EditFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = EditViewModelFactory(dataSource, arguments.note)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.navigateToMainOnSave.observe(viewLifecycleOwner, Observer {onSave->
            if (onSave == true) {
                val title = binding.EditTitle.text.toString()
                val noteText  = binding.EditTextNote.text.toString()
                viewModel.updateNote(title, noteText)
                viewModel.saveEditNote(viewModel.getNote().value!!)
                this.findNavController()
                    .navigate(EditFragmentDirections.actionEditFragmentToMainFragment())
                viewModel.navigateCompleted()
            }
        })
        viewModel.navigateToMainOnDelete.observe(viewLifecycleOwner, Observer { onDelete->
            if (onDelete == true){
                viewModel.deleteNote(viewModel.getNote().value!!.noteId)
                this.findNavController()
                    .navigate(EditFragmentDirections.actionEditFragmentToMainFragment())
                viewModel.navigateCompleted()
            }
        })

        return binding.root
    }


}