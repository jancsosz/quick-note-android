package com.example.quicknote.notes

import android.app.Application
import androidx.lifecycle.*
import com.example.quicknote.database.Note
import com.example.quicknote.database.NoteDatabaseDao
import kotlinx.coroutines.launch

class NotesViewModel(
    dataSource: NoteDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val database = dataSource

    val notes = database.getAllNotes()

    private suspend fun insert(note: Note) {
        database.insert(note)
    }

    private val _navigateToAddNote = MutableLiveData<Boolean?>()

    val navigateToAddNote: LiveData<Boolean?>
        get() = _navigateToAddNote

    private val _navigateToNoteDetail = MutableLiveData<Boolean?>()

    val navigateToNoteDetail: LiveData<Boolean?>
        get() = _navigateToNoteDetail

    fun onAddNoteClick(){
        _navigateToAddNote.value = true
    }

    fun doneNavigating() {
        _navigateToAddNote.value = null
    }

    init {
        viewModelScope.launch {
            val note = Note(0, "elso", "uzenet", "jancsosz")
            insert(note)
        }
    }
}