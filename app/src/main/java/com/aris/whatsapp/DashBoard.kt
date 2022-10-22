package com.aris.whatsapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.aris.whatsapp.adapters.Pageradapter
import com.google.android.material.tabs.TabLayout

class DashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        val dashboardview = findViewById<ViewPager>(R.id.dashboardview)
        val tablayout = findViewById<TabLayout>(R.id.tablayout)
        supportActionBar!!.title = "Dashboard"

        val adaptor = Pageradapter(supportFragmentManager)
        dashboardview.adapter = adaptor

        tablayout.setupWithViewPager(dashboardview)
        tablayout.setTabTextColors(Color.WHITE, Color.GREEN)

        val name = intent!!.extras!!.getString("name")
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }
}