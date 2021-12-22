package com.example.quicknote.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.database.Note
import com.example.quicknote.database.NoteDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNoteViewModel(
    noteKey: Long = 0L,
    dataSource: NoteDatabaseDao
) : ViewModel() {

    val database = dataSource

    private val _navigateToNotes = MutableLiveData<Boolean?>()

    val navigateToNotes: LiveData<Boolean?>
        get() = _navigateToNotes

    private suspend fun insert(note: Note) {
        withContext(Dispatchers.IO){
            database.insert(note)
        }
    }

    fun saveNote(title: String, author: String, message: String) {
        viewModelScope.launch {
            val note = Note(0L, title, message, author)
            insert(note)
        }
    }

    fun onAddNoteClick(){
        _navigateToNotes.value = true
    }

    fun doneNavigating() {
        _navigateToNotes.value = null
    }
}