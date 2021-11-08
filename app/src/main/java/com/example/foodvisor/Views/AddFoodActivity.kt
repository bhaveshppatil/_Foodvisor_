package com.example.foodvisor.Views

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.foodvisor.Adapter.ViewPagerAdapter
import com.example.foodvisor.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_add_food.*


class AddFoodActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private var handler = Handler()
    private lateinit var pgsBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        var intent = Intent()
        intent = getIntent()
        val category = intent.getStringExtra("category")
        lunch_category.text = category

        pgsBar = findViewById<View>(R.id.progressBar4) as ProgressBar

        pgsBar.max = 650
        var i = 0;

        BottomSheetBehavior.from(design_bottom_sheet).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        supportActionBar?.hide()

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Search"))
        tabLayout.addTab(tabLayout.newTab().setText("Photo"))
        tabLayout.addTab(tabLayout.newTab().setText("Favorites"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = ViewPagerAdapter(this, supportFragmentManager, tabLayout.tabCount)

        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                tabLayout.getTabAt(0)?.icon?.setColorFilter(
                    resources.getColor(android.R.color.black),
                    PorterDuff.Mode.SRC_IN
                );
                if (tabLayout.getTabAt(1)?.isSelected == true){
                    if (i <= 650) {
                        i += 15
                        // Update the progress bar and display the current value in text view
                        pgsBar.progress = i
                        update_progress.text = "${i.toString()} / ${pgsBar.max} "
                        Toast.makeText(this@AddFoodActivity, "Calories updated", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tabLayout.getTabAt(2)?.icon
                    ?.setColorFilter(
                        resources.getColor(android.R.color.white),
                        PorterDuff.Mode.SRC_IN
                    );
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

}