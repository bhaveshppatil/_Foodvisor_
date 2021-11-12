package com.example.foodvisor.Views

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.foodvisor.Fragments.HomeScreenFragments.*
import com.example.foodvisor.R

class HomeScreen : AppCompatActivity() {

    private lateinit var bottomNavigation: MeowBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        bottomNavigation = findViewById(R.id.bottom_navigation)

        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.home_24))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_baseline_coffee_24))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_baseline_add_24))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.heart_with_pulse_50))
        bottomNavigation.add(MeowBottomNavigation.Model(5, R.drawable.ic_baseline_bar_chart_24))

        bottomNavigation.setOnShowListener { item ->
            var fragment: Fragment? = null
            when (item.id) {
                1 -> fragment = HomeFragment()
                2 -> fragment = RecipeFragment()
                3 -> fragment = AddFoodFragment()
                4 -> fragment = DietsFragment()
                5 -> fragment = ProgressFragment()
            }
            loadFragment(fragment)
        }

        bottomNavigation.setCount(4, "10")
        bottomNavigation.show(3, true)

        bottomNavigation.setOnClickMenuListener {

        }

        bottomNavigation.setOnReselectListener {

        }
    }

    private fun loadFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment!!).commit()
    }
}