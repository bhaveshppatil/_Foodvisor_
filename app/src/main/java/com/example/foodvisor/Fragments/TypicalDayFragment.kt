package com.example.foodvisor.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodvisor.R
import kotlinx.android.synthetic.main.fragment_goal.*
import kotlinx.android.synthetic.main.fragment_typical_day.*

class TypicalDayFragment : Fragment(R.layout.fragment_typical_day) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        ivTypicalDayArrow.setOnClickListener {
            navController.navigate(R.id.action_typicalDayFragment_to_privacyPolicy)
        }
    }
}