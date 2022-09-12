package com.route.news_offlinec35

import android.os.Bundle
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

class HomeActivity : AppCompatActivity() {

  //  private lateinit var appBarConfiguration: AppBarConfiguration
   lateinit var drawerLayout: DrawerLayout
   lateinit var drawerImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_home)
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerImage = findViewById(R.id.drawer_image)
        drawerImage.setOnClickListener {
            drawerLayout.open()
        }
    }
}
