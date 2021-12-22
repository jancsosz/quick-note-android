/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.quicknote.notedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.quicknote.database.Note
import com.example.quicknote.database.NoteDatabaseDao

/**
 * ViewModel for SleepQualityFragment.
 *
 * @param noteKey The key of the current night we are working on.
 */
class NoteDetailViewModel(
    private val noteKey: Long = 0L,
    dataSource: NoteDatabaseDao) : ViewModel() {

    val database = dataSource

    private val note: LiveData<Note>

    fun getNote() = note

    val titleString = Transformations.map(getNote()) { note ->
        "Title: " + note.title
    }

    val authorString = Transformations.map(getNote()) { note ->
        "Author: " + note.author
    }

    private val _navigateToNotes = MutableLiveData<Boolean?>()

    val navigateToNotes: LiveData<Boolean?>
        get() = _navigateToNotes

    init {
        note = database.getNoteWithId(noteKey)
    }

    fun doneNavigating() {
        _navigateToNotes.value = null
    }

    fun onClose() {
        _navigateToNotes.value = true
    }
}
