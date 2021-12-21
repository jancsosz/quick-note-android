package com.example.quicknote.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknote.R
import com.example.quicknote.TextItemViewHolder
import com.example.quicknote.database.Note


class NoteAdapter: RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var data =  listOf<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_note,
                parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val noteTitle: TextView = itemView.findViewById(R.id.note_title)
        val noteAuthor: TextView = itemView.findViewById(R.id.note_author)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.noteTitle.text = item.title
        holder.noteAuthor.text = item.author
    }
}

