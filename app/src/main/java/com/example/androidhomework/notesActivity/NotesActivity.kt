package com.example.androidhomework.notesActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.androidhomework.R
import com.example.androidhomework.notesData.MyDbManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class NotesActivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    val myAdapter = Adapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notes)
        val addNoteButton: FloatingActionButton = findViewById(R.id.add_button)
        addNoteButton.setOnClickListener {
            val intent = Intent(this.baseContext, NotesAddActivity::class.java)
            startActivity(intent)
        }
        init()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
    fun  init(){
        findViewById<RecyclerView>(R.id.rcView).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rcView).adapter = myAdapter

    }
    fun fillAdapter(){
        myAdapter.updateAdapter(myDbManager.readDbData())
    }
}