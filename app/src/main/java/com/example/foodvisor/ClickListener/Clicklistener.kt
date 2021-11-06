package com.example.foodvisor.ClickListener

import com.example.foodvisor.Model.BreakfastModel
import com.example.foodvisor.Model.DinnerModel
import com.example.foodvisor.Model.DrinksModel
import com.example.foodvisor.Model.LunchModel

interface Clicklistener {

    fun onBreakfastRecipeClick(breakfastModel: BreakfastModel)
    fun onLunchRecipeClick(lunchModel: LunchModel)
    fun onDinnerRecipeClick(dinnerModel: DinnerModel)
    fun onDrinksRecipeClick(drinksModel: DrinksModel)
}
