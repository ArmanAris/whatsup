package com.aris.whatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aris.whatsapp.R

class Setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar!!.title = "Settings"
    }
}