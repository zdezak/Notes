package com.zdez.notes.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.zdez.notes.R
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

        val binding: MainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.id.mainFragment, container, false)
        binding.addButton.setOnClickListener{view:View->
            Navigation.findNavController(view)
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}