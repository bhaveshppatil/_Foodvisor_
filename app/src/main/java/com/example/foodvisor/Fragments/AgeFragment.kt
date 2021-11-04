package com.example.foodvisor.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodvisor.R
import kotlinx.android.synthetic.main.fragment_age.*

class AgeFragment : Fragment(R.layout.fragment_age) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        ivAgeArrow.setOnClickListener {
            var age = etUserAge.text.toString()
            if (age.isEmpty()) {
                etUserAge.error = "Empty age field"
            } else {
                navController.navigate(R.id.action_ageFragment_to_heightFragment)
            }
        }
    }
}