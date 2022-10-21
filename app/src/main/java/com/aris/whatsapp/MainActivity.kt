package com.aris.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mAuthlistener: FirebaseAuth.AuthStateListener? = null
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        mAuthlistener = FirebaseAuth.AuthStateListener { firebaseAuth: FirebaseAuth ->
            user = firebaseAuth.currentUser

            if (user != null) {
                val intent = Intent(this, DashBoard::class.java)
                val email1 = user!!.email!!.split("@")[0]
                intent.putExtra("name", email1)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val btn_sign = findViewById<Button>(R.id.btn_sign)
        val btn_cr = findViewById<Button>(R.id.btn_cr)

        btn_sign.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        btn_cr.setOnClickListener {
            startActivity(Intent(this, Createac::class.java))
        }


    }

    override fun onStart() {
        super.onStart()

        mAuth!!.addAuthStateListener(mAuthlistener!!)

    }

    override fun onStop() {
        super.onStop()

        if(mAuthlistener !=null){
            mAuth!!.removeAuthStateListener(mAuthlistener!!)
        }


    }
}
