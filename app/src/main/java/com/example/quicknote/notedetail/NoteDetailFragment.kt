package com.example.quicknote.notedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.quicknote.R
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

        return binding.root
    }
}