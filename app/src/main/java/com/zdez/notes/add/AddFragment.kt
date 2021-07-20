package com.zdez.notes.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.zdez.notes.R
import com.zdez.notes.database.NotesDatabase
import com.zdez.notes.databinding.AddFragmentBinding

class AddFragment : Fragment() {

    companion object {
        fun newInstance() = AddFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = AddFragmentBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = NotesDatabase.getInstance(application).notesDao
        val viewModelFactory = AddViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(AddViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToNoteList.observe(viewLifecycleOwner, Observer {
            if(it==true){
                this.findNavController().navigate(R.id.action_addFragment_to_mainFragment)
                viewModel.doneNavigation()
            }
        })
        return binding.root
    }
}