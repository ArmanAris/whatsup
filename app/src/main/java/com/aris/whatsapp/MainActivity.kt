package com.aris.whatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private var mAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        mAuth!!.signInWithEmailAndPassword("ashil_troy@yahoo.com", "ashil71")
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "user sign in Successful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "user sign in UnSuccessful", Toast.LENGTH_LONG).show()
                }
            }

//        var databaseA = FirebaseDatabase.getInstance()
//        var databaseref = databaseA.getReference("message").push()
//        databaseref.setValue("Arman Aris")
//
//
//        databaseref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                var data = snapshot.value as HashMap<String , Any>
//                data.get("Key")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
    }

    override fun onStart() {
        super.onStart()

        user = mAuth!!.currentUser
        if (user != null) {
            Toast.makeText(this, "user login", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "user logout", Toast.LENGTH_LONG).show()
        }

    }
}
