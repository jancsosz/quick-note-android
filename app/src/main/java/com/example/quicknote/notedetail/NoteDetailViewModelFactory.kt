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


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quicknote.database.NoteDatabaseDao

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the SleepDatabaseDao and context to the ViewModel.
 */
class NoteDetailViewModelFactory(
    private val noteKey: Long,
    private val dataSource: NoteDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailViewModel::class.java)) {
            return NoteDetailViewModel(noteKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}