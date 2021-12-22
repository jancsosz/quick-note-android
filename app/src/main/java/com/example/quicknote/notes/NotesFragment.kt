package com.example.quicknote.notes

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
import com.example.quicknote.databinding.FragmentNotesBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNotesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_notes,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = NotesViewModelFactory(dataSource, application)

        val notesViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(NotesViewModel::class.java)

        binding.notesViewModel = notesViewModel

        binding.setLifecycleOwner(this)

        notesViewModel.navigateToAddNote.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
                )
                notesViewModel.doneNavigating()
            }
        })

        val adapter = NoteAdapter(NoteListener { noteId ->
            //Toast.makeText(context, "${nightId}", Toast.LENGTH_LONG).show()
            notesViewModel.onNoteClicked(noteId)
        })
        binding.noteList.adapter = adapter

        notesViewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        notesViewModel.navigateToNoteDetail.observe(viewLifecycleOwner, Observer { note ->
            note?.let {

                this.findNavController().navigate(
                    NotesFragmentDirections
                        .actionNotesFragmentToNoteDetailFragment(note))
                notesViewModel.onNoteDetailNavigated()
            }
        })

        return binding.root
    }
}