package com.example.motionassignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = findViewById<FragmentContainerView>(R.id.fragment_bnv)
        val bottomNav = findViewById<BottomNavigationView>(R.id.main_bnv)

        bottomNav.setupWithNavController(fragment.findNavController())
    }
}