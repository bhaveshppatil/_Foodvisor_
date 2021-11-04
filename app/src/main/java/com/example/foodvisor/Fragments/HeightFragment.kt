package com.example.foodvisor.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodvisor.R
import kotlinx.android.synthetic.main.fragment_age.*
import kotlinx.android.synthetic.main.fragment_height.*

class HeightFragment : Fragment(R.layout.fragment_height) {
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        ivHeightArrow.setOnClickListener {
            var height = etUserHeight.text.toString()
            if (height.isEmpty()) {
                etUserHeight.error = "Empty height field"
            } else {
                navController.navigate(R.id.action_heightFragment_to_weightFragment)
            }
        }
    }
}