package com.aris.whatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val Email1 = findViewById<EditText>(R.id.EmailAddress)
        val password1 = findViewById<EditText>(R.id.Password)
        val btn = findViewById<Button>(R.id.button)


        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        btn.setOnClickListener {
            val Email = Email1.text.toString()
            val password = password1.text.toString()
            if (Email.trim().isNotEmpty() && password.trim().isNotEmpty()) {
                mAuth.createUserWithEmailAndPassword(Email, password)
                    .addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            val user: FirebaseUser = mAuth.currentUser!!
                            Toast.makeText(this, user.email.toString(), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Errooooooor!!!!!", Toast.LENGTH_SHORT).show()
                        }
                    }

            } else {
                Toast.makeText(this, "اطلاعات را کامل کنید!!!!!!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}
