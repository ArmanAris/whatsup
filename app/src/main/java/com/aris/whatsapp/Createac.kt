package com.aris.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Objects

class Createac : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mdatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createac)

        mAuth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.ca).setOnClickListener {
            val name = findViewById<EditText>(R.id.nameca).text.toString().trim()
            val email = findViewById<EditText>(R.id.EmailAddressca).text.toString().trim()
            val pass = findViewById<EditText>(R.id.Passwordca).text.toString().trim()

            if (name.isNotEmpty() && !TextUtils.isEmpty(email) && pass.isNotEmpty()) {
                create(name, email, pass)
            } else {
                Toast.makeText(this, "Enter All Information", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun create(name: String, email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User Create", Toast.LENGTH_SHORT).show()

                    val user = mAuth!!.currentUser
                    val id = user!!.uid

                    mdatabase = FirebaseDatabase.getInstance().reference.child("Users").child(id)

                    val Objectsdata = HashMap<String, String>()
                    Objectsdata.put("Name", name)
                    Objectsdata.put("Email", email)
                    Objectsdata.put("PassWord", password)
                    Objectsdata.put("Image", "default")

                    mdatabase!!.setValue(Objectsdata).addOnCompleteListener { task: Task<Void> ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, DashBoard::class.java)
                            intent.putExtra("name", name)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "User Not Add to DataBase", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }


                } else {
                    Toast.makeText(this, "User Not Create", Toast.LENGTH_SHORT).show()
                }

            }
    }
}