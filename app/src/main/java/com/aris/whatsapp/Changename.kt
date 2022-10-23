package com.aris.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Changename : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changename)

        supportActionBar!!.title = "Change Name"

        if (intent.extras != null) {
            val data = intent.extras!!.getString("name")
            findViewById<EditText>(R.id.editTextTextPersonName).setText(data)
        }


        findViewById<Button>(R.id.button).setOnClickListener {
            val userid = FirebaseAuth.getInstance().currentUser!!.uid
            val database = FirebaseDatabase.getInstance().reference.child("Users").child(userid)
            database.child("Name")
                .setValue(findViewById<EditText>(R.id.editTextTextPersonName).text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Name Changed", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Setting::class.java))
                    } else {
                        Toast.makeText(this, "Name Not Chang", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}