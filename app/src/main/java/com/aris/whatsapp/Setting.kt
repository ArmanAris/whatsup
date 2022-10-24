package com.aris.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Setting : AppCompatActivity() {

    val GALLERY_ID: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar!!.title = "Settings"
        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        val database = FirebaseDatabase.getInstance().reference.child("Users").child(userid)


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("Name").value
                val email = snapshot.child("Email").value
                val password = snapshot.child("PassWord").value
                val image = snapshot.child("Image").value

                findViewById<TextView>(R.id.namesetting).text = name.toString()
                findViewById<TextView>(R.id.emailsetting).text = email.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        findViewById<Button>(R.id.changname).setOnClickListener {
            val intent = Intent(this, Changename::class.java)
            intent.putExtra("name", findViewById<TextView>(R.id.namesetting).text)
            startActivity(intent)
        }

        findViewById<Button>(R.id.changpic).setOnClickListener {
            val galleryIntent = Intent()
            galleryIntent.type = "image/*"
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(galleryIntent, "choose image"), GALLERY_ID)

        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}