package com.example.ululazmi

import android.widget.Toast
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception
import android.widget.ImageView



class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(fragment_home())
        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)!!
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(fragment_home())
                    true
                }
                R.id.navigation_about -> {
                    loadFragment(fragment_about)
                    true
                }
                R.id.navigation_quiz -> {
                    loadFragment(fragment_quiz())
                    true
                }
                else -> false
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.bottomNavigation,fragment)
        transaction.commit()
    }
}
