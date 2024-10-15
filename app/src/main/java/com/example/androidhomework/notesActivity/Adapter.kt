package com.example.androidhomework.notesActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.notesData.ListItem
import com.example.androidhomework.notesData.MyIntent

class Adapter(listMain: ArrayList<ListItem>, contextM: Context) :
    RecyclerView.Adapter<Adapter.Holder>() {
    var listArray = listMain
    var context = contextM

    class Holder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {

        val textTitle = itemView.findViewById<TextView>(R.id.textTitle)
        val context = contextV
        fun setData(item: ListItem) {
            textTitle.text = item.title
            itemView.setOnClickListener {
                val intent = Intent(context, NotesAddActivity::class.java).apply {
                    putExtra(MyIntent.I_TITLE_KEY, item.title)
                    putExtra(MyIntent.I_CONTENT_KEY, item.content)
                }
                context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.rc_item, parent, false), context)
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(listArray.get(position))
    }

    fun updateAdapter(listItems: List<ListItem>) {
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }
}