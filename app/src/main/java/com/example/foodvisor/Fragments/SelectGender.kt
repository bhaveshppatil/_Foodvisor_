package com.example.foodvisor.Fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodvisor.R
import kotlinx.android.synthetic.main.fragment_select_gender.*


class SelectGender : Fragment(R.layout.fragment_select_gender) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val ivFemale = view.findViewById<ImageView>(R.id.ivFemale);
        val ivMale = view.findViewById<ImageView>(R.id.ivMale);

        ivFemale.setOnClickListener {
            Toast.makeText(context, "Gender Female Selected", Toast.LENGTH_SHORT).show()
        }
        ivMale.setOnClickListener {
            Toast.makeText(context, "Gender Male Selected", Toast.LENGTH_SHORT).show()
        }

        ivGenderArrow.setOnClickListener {
            navController.navigate(R.id.action_selectGender_to_ageFragment)
        }

    }

}