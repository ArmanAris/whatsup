package com.aris.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class login : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mdatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        val Email = findViewById<EditText>(R.id.EmailAddress)
        val Password = findViewById<EditText>(R.id.Password)
        val btn = findViewById<Button>(R.id.login)

        btn.setOnClickListener {
            val email =Email.text.toString().trim()
            val password = Password.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                login(email,password)
            }else{
                Toast.makeText(this, "Enter All Information", Toast.LENGTH_SHORT).show()
            }



        }

    }

    private fun login(email:String,password:String){
        mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                task: Task<AuthResult> ->
            if (task.isSuccessful){
                val intent = Intent(this, DashBoard::class.java)
                val email1=email.split("@")[0]
                intent.putExtra("name", email1)
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        }
    }
