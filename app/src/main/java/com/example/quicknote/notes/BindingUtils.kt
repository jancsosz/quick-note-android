package com.example.quicknote.notes

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.quicknote.database.Note

@BindingAdapter("titleString")
fun TextView.setTitleString(item: Note?) {
    item?.let {
        val titleText = "Title: " + item.title
        text = titleText
    }
}

@BindingAdapter("authorString")
fun TextView.setAuthorString(item: Note?) {
    item?.let {
        val authorText = "Author: " + item.author
        text = authorText
    }
}

@BindingAdapter("messageString")
fun TextView.setMessageString(item: Note?) {
    item?.let {
        val messageText = "Message: " + item.message
        text = messageText
    }
}