package com.example.quicknote.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "message")
    var message: String,

    @ColumnInfo(name = "author_name")
    var author: String,

    @ColumnInfo(name = "last_modified")
    var lastModified: Date
)
