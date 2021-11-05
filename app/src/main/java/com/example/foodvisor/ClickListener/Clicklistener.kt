package com.example.foodvisor.ClickListener

import com.example.foodvisor.Model.BreakfastModel
import com.example.foodvisor.Model.LunchModel

interface Clicklistener {

    fun onBreakfastRecipeClick(breakfastModel: BreakfastModel)
    fun onLunchRecipeClick(lunchModel: LunchModel)

}
