package com.example.quicknote.notedetail

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
import com.example.quicknote.databinding.FragmentNoteDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NoteDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNoteDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_note_detail,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val arguments = NoteDetailFragmentArgs.fromBundle(requireArguments())

        // Create an instance of the ViewModel Factory.
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = NoteDetailViewModelFactory(arguments.noteId, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val noteDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(NoteDetailViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.noteDetailViewModel = noteDetailViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.
        noteDetailViewModel.navigateToNotes.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    NoteDetailFragmentDirections.actionNoteDetailFragmentToNotesFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                noteDetailViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}