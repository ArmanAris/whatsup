package com.aris.whatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var databaseA = FirebaseDatabase.getInstance()
        var databaseref = databaseA.reference
        databaseref.setValue("Arman Aris")
    }
}