package com.example.foodvisor.Views

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.foodvisor.Adapter.ViewPagerAdapter
import com.example.foodvisor.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.masai.myjournalapp.Repository.FoodRepository
import com.masai.myjournalapp.RoomDatabase.FoodDAO
import com.masai.myjournalapp.RoomDatabase.FoodRoomDB
import com.masai.myjournalapp.ViewModel.FoodViewModel
import com.masai.myjournalapp.ViewModel.FoodViewModelFactory
import kotlinx.android.synthetic.main.activity_add_food.*


class AddFoodActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private var handler = Handler()
    private lateinit var pgsBar: ProgressBar
    var total: Int = 0
    private lateinit var foodDAO: FoodDAO
    private lateinit var foodViewModel: FoodViewModel

    override fun onStart() {
        super.onStart()
        var intent = Intent()
        intent = getIntent()
        val category = intent.getStringExtra("category")
        lunch_category.text = category

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        BottomSheetBehavior.from(design_bottom_sheet).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        supportActionBar?.hide()

        foodDAO = FoodRoomDB.getDatabaseObject(this).getFoodDAO()
        val foodRepository = FoodRepository(foodDAO)
        val foodViewModelFactory = FoodViewModelFactory(foodRepository)
        foodViewModel =
            ViewModelProviders.of(this, foodViewModelFactory).get(FoodViewModel::class.java)

        pgsBar = findViewById<View>(R.id.progressBar4) as ProgressBar
        pgsBar.max = 650
        foodViewModel.getCaloriesData().observe(this, Observer {
            total = if (it.toString().isBlank()) {
                0;
            } else {
                it
            }
            if (total <= 650) {
                pgsBar.progress = total
                update_progress.text = "${total.toString()} / ${pgsBar.max} "
            } else {
                total = 0
            }
        })

        pgsBar.progress = total
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