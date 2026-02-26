package com.example.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.presentation.fragments.FragmentAccount
import com.example.presentation.fragments.FragmentFavorites
import com.example.presentation.fragments.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        if (savedInstanceState == null) {
            openFragment(MainFragment())
        }

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main -> {
                    openFragment(MainFragment())
                    true
                }R.id.menu_favorites -> {
                openFragment(FragmentFavorites())
                true
            }
                R.id.menu_account -> {
                    openFragment(FragmentAccount()) // если есть
                    true
                }
                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, fragment)
            .commit()
    }
}