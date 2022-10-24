package com.aris.whatsapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.aris.whatsapp.adapters.Pageradapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

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
        tablayout.setTabTextColors(Color.WHITE, Color.GRAY)

        val name = intent!!.extras!!.getString("name")
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        if (item.itemId == R.id.logoutid) {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        if (item.itemId == R.id.settingid) {
            startActivity(Intent(this, Setting::class.java))
        }

        return true
    }

}