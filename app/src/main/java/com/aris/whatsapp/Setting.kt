package com.aris.whatsapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.*

class Setting : AppCompatActivity() {

    lateinit var database: DatabaseReference
    lateinit var userid: FirebaseUser
    lateinit var mStorage: StorageReference

    val GALLERY_ID: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar!!.title = "Settings"
        userid = FirebaseAuth.getInstance().currentUser!!
        val id = userid.uid

        database = FirebaseDatabase.getInstance().reference.child("Users").child(id)

        mStorage = FirebaseStorage.getInstance().reference

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

        if (requestCode == GALLERY_ID && resultCode == Activity.RESULT_OK) {

            val images: Uri? = data!!.data

            val des: String = StringBuilder(UUID.randomUUID().toString()).toString()
            UCrop.of(Uri.parse(images.toString()), Uri.fromFile(File(cacheDir, des)))
                .withAspectRatio(1f, 1f)
                .withMaxResultSize(2000, 2000).start(this)
        }

        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            val resultUri = UCrop.getOutput(data!!)
            val id = userid.uid
            val imagefile = File(resultUri!!.path)
        }


        // super.onActivityResult(requestCode, resultCode, data)
    }
}