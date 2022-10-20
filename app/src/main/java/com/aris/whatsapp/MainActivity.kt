package com.aris.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_sign = findViewById<Button>(R.id.btn_sign)
        val btn_cr = findViewById<Button>(R.id.btn_cr)

        btn_sign.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        btn_cr.setOnClickListener {
            startActivity(Intent(this,Createac::class.java))
        }


    }
}
