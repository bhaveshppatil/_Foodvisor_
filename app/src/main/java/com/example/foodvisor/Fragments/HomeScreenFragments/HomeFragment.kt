package com.example.foodvisor.Fragments.HomeScreenFragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airbnb.lottie.LottieAnimationView
import com.example.foodvisor.R
import com.example.foodvisor.Views.AddFoodActivity
import com.example.foodvisor.Views.PremiumUnlocked
import com.masai.myjournalapp.Repository.FoodRepository
import com.masai.myjournalapp.RoomDatabase.FoodDAO
import com.masai.myjournalapp.RoomDatabase.FoodRoomDB
import com.masai.myjournalapp.ViewModel.FoodViewModel
import com.masai.myjournalapp.ViewModel.FoodViewModelFactory
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var count: Double = 0.00
    var total: Int = 0
    var totalCalories: Int = 0
    private lateinit var circularProgressBar: CircularProgressBar
    private lateinit var foodDAO: FoodDAO
    private lateinit var foodViewModel: FoodViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        addFoodToList()
        circularProgressbar()

        foodDAO = context?.let { FoodRoomDB.getDatabaseObject(it).getFoodDAO() }!!
        val foodRepository = FoodRepository(foodDAO)
        val foodViewModelFactory = FoodViewModelFactory(foodRepository)
        foodViewModel =
            ViewModelProviders.of(this, foodViewModelFactory).get(FoodViewModel::class.java)

        foodViewModel.getCaloriesData().observe(viewLifecycleOwner, Observer {
            total = it ?: 0
            totalCalories = total * 4
            if (total <= 812) {
                tvBreakfastCal.text = "${total.toString()} / 508 Cal"
                tvLunchCal.text = "${total.toString()} / 812 Cal"
                tvDinnerCal.text = "${total.toString()} / 609 Cal"
                tvDrinksCal.text = "${total.toString()} / 720 Cal"
                tvHomeCal.text = "${totalCalories.toFloat()} \nCal"
            }
            circularProgressBar.setProgressWithAnimation(totalCalories.toFloat() + 0f, 1000) // =1s
        })

        btnPremiumHome.setOnClickListener {
            val intent = Intent(context, PremiumUnlocked::class.java)
            startActivity(intent)
        }
    }

    private fun initViews(view: View) {

        circularProgressBar = view.findViewById<CircularProgressBar>(R.id.circularProgressBar)
        val animationView1 = view.findViewById<LottieAnimationView>(R.id.animationView1)
        val animationView2 = view.findViewById<LottieAnimationView>(R.id.animationView2)
        val animationView3 = view.findViewById<LottieAnimationView>(R.id.animationView3)
        val animationView4 = view.findViewById<LottieAnimationView>(R.id.animationView4)
        val animationView5 = view.findViewById<LottieAnimationView>(R.id.animationView5)
        animationView1.setOnClickListener {
            count += 0.25
            tvWaterGoal.text = "$count L"
            animationView1.playAnimation()
        }
        animationView2.setOnClickListener {
            count += 0.25
            tvWaterGoal.text = "$count L"
            animationView2.playAnimation()
        }
        animationView3.setOnClickListener {
            count += 0.25
            tvWaterGoal.text = "$count L"
            animationView3.playAnimation()
        }
        animationView4.setOnClickListener {
            count += 0.25
            tvWaterGoal.text = "$count L"
            animationView4.playAnimation()
        }

        animationView5.setOnClickListener {
            count += 0.25
            tvWaterGoal.text = "$count L"
            animationView5.playAnimation()
        }
    }

    private fun addFoodToList() {
        ivBreakfastAdd.setOnClickListener {
            val intent = Intent(context, AddFoodActivity::class.java)
            intent.putExtra("category", "Breakfast")
            startActivity(intent)
        }
        ivLunchAdd.setOnClickListener {
            val intent = Intent(context, AddFoodActivity::class.java)
            intent.putExtra("category", "Lunch")
            startActivity(intent)
        }
        ivDinnerAdd.setOnClickListener {
            val intent = Intent(context, AddFoodActivity::class.java)
            intent.putExtra("category", "Dinner")
            startActivity(intent)
        }
        ivDrinksAdd.setOnClickListener {
            val intent = Intent(context, AddFoodActivity::class.java)
            intent.putExtra("category", "Drinks")
            startActivity(intent)
        }
    }

    private fun circularProgressbar() {
        circularProgressBar.apply {

            // Set Progress
            progress = 65f
            // Set Progress Max
            progressMax = 2570f

            // Set ProgressBar Color
            progressBarColor = Color.BLACK
            // or with gradient
            progressBarColorStart = Color.GRAY
            progressBarColorEnd = Color.BLUE
            progressBarColorDirection = CircularProgressBar.GradientDirection.LEFT_TO_RIGHT

            // Set Width
            progressBarWidth = 5f // in DP
            backgroundProgressBarWidth = 10f // in DP

            // Other
            roundBorder = true
            startAngle = 180f
            progressDirection = CircularProgressBar.ProgressDirection.TO_LEFT
        }
    }
}