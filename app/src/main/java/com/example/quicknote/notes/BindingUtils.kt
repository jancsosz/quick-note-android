package com.example.quicknote.notes

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.quicknote.database.Note

@BindingAdapter("titleString")
fun TextView.setTitleString(item: Note?) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("authorString")
fun TextView.setAuthorString(item: Note?) {
    item?.let {
        text = item.author
    }
}

@BindingAdapter("messageString")
fun TextView.setMessageString(item: Note?) {
    item?.let {
        text = item.message
    }
}