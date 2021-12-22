package com.example.quicknote.notes

import android.app.Application
import androidx.lifecycle.*
import com.example.quicknote.database.Note
import com.example.quicknote.database.NoteDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(
    dataSource: NoteDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val database = dataSource

    val notes = database.getAllNotes()

    private val _navigateToAddNote = MutableLiveData<Boolean?>()

    val navigateToAddNote: LiveData<Boolean?>
        get() = _navigateToAddNote

    private val _navigateToNoteDetail = MutableLiveData<Long>()

    val navigateToNoteDetail: LiveData<Long>
        get() = _navigateToNoteDetail

    suspend fun clear() {
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }


    fun onAddNoteClick(){
        _navigateToAddNote.value = true
    }

    fun doneNavigating() {
        _navigateToAddNote.value = null
    }


    fun onNoteClicked(id: Long) {
        _navigateToNoteDetail.value = id
    }

    fun onNoteDetailNavigated() {
        _navigateToNoteDetail.value = null
    }
}