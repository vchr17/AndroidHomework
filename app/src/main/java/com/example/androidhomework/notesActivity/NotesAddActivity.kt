package com.example.androidhomework.notesActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidhomework.R
import com.example.androidhomework.notesData.MyDbManager
import com.example.androidhomework.notesData.MyIntent
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesAddActivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notes_add)

        val goBackButton : FloatingActionButton = findViewById(R.id.backButton)
        goBackButton.setOnClickListener{
            val intent = Intent(this.baseContext, NotesActivity::class.java)
            startActivity(intent)
        }
        val saveButton : FloatingActionButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener{
            save()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getMyIntent()
    }

    private fun save(){
        val myTitle = findViewById<EditText>(R.id.titleOfNote).text.toString()
        val myContent = findViewById<EditText>(R.id.noteText).text.toString()
        if (myTitle != "" && myContent != ""){
            myDbManager.insertToDb(myTitle, myContent)
            findViewById<EditText>(R.id.titleOfNote).text.clear()
            findViewById<EditText>(R.id.noteText).text.clear()
            val intent = Intent(this.baseContext, NotesActivity::class.java)
            Toast.makeText(this, R.string.note_saved, Toast.LENGTH_LONG).show()
            startActivity(intent)
        }else {
            Toast.makeText(this, R.string.save_failed, Toast.LENGTH_LONG).show()
        }


    }
    private fun getMyIntent(){
        val i = intent
        if(i!=null){
            if(i.getStringExtra(MyIntent.I_TITLE_KEY)!= "null"){
                findViewById<EditText>(R.id.titleOfNote).setText(i.getStringExtra(MyIntent.I_TITLE_KEY))
                findViewById<EditText>(R.id.noteText).setText(i.getStringExtra(MyIntent.I_CONTENT_KEY))

            }
        }
    }


    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}