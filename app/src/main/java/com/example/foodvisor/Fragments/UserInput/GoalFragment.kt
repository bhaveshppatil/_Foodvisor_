package com.example.foodvisor.Fragments.UserInput

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodvisor.R
import kotlinx.android.synthetic.main.fragment_goal.*

class GoalFragment : Fragment(R.layout.fragment_goal) {
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        crdLoseWeight.setOnClickListener {
            navController.navigate(R.id.action_goalFragment_to_typicalDayFragment)
        }
        crdEatHealthy.setOnClickListener {
            navController.navigate(R.id.action_goalFragment_to_typicalDayFragment)
        }
        crdMuscles.setOnClickListener {
            navController.navigate(R.id.action_goalFragment_to_typicalDayFragment)
        }
    }
}