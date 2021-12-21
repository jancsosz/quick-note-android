package com.example.quicknote.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quicknote.database.Note
import com.example.quicknote.database.NoteDatabaseDao

class AddNoteViewModel(
    noteKey: Long = 0L,
    dataSource: NoteDatabaseDao
) : ViewModel() {

    val database = dataSource

    private val _navigateToNotes = MutableLiveData<Boolean?>()

    val navigateToNotes: LiveData<Boolean?>
        get() = _navigateToNotes

    fun onAddNoteClick(){



        _navigateToNotes.value = true
    }

    fun doneNavigating() {
        _navigateToNotes.value = null
    }
}