package com.example.foodvisor.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodvisor.R
import kotlinx.android.synthetic.main.fragment_weight.*


class WeightFragment : Fragment(R.layout.fragment_weight) {
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        ivWeightArrow.setOnClickListener {
            navController.navigate(R.id.action_weightFragment_to_goalFragment)
        }
    }

}