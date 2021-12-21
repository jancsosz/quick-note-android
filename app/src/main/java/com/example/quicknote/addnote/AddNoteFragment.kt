package com.example.quicknote.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quicknote.R
import com.example.quicknote.database.NoteDatabase
import com.example.quicknote.databinding.FragmentAddNoteBinding
import com.example.quicknote.notes.NotesFragmentDirections
import com.example.quicknote.notes.NotesViewModel
import com.example.quicknote.notes.NotesViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [AddNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddNoteBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_note,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = AddNoteViewModelFactory(0L, dataSource)

        val notesViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(AddNoteViewModel::class.java)

        binding.addNoteViewModel = notesViewModel

        binding.setLifecycleOwner(this)

        notesViewModel.navigateToNotes.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    AddNoteFragmentDirections.actionAddNoteFragmentToNotesFragment()
                )
                notesViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}