package com.example.foodvisor.Fragments.HomeScreenFragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.foodvisor.R
import com.example.foodvisor.Views.AddFoodActivity
import com.ramotion.circlemenu.CircleMenuView


class AddFoodFragment : Fragment(R.layout.fragment_add_food) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menu = view.findViewById<CircleMenuView>(R.id.circle_menu)

        menu.eventListener = object : CircleMenuView.EventListener() {
            override fun onMenuOpenAnimationStart(view: CircleMenuView) {
                Log.d("D", "onMenuOpenAnimationStart")
            }

            override fun onButtonClickAnimationStart(view: CircleMenuView, index: Int) {
                when (index) {
                    0 -> {
                        val intent = Intent(context, AddFoodActivity::class.java)
                        startActivity(intent)
                    }
                    1 -> {
                        val intent = Intent(context, AddFoodActivity::class.java)
                        startActivity(intent)
                    }
                    2 -> {
                        val intent = Intent(context, AddFoodActivity::class.java)
                        startActivity(intent)
                    }
                    3 -> {
                        val intent = Intent(context, AddFoodActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

            override fun onButtonClickAnimationEnd(view: CircleMenuView, index: Int) {
                Log.d("D", "onButtonClickAnimationEnd| index: $index")
            }

            override fun onButtonLongClick(view: CircleMenuView, index: Int): Boolean {
                Log.d("D", "onButtonLongClick| index: $index")
                return true
            }
        }
    }

}