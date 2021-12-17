package com.masai.foodvisor.clickListener

interface Clicklistener {

    fun onBreakfastRecipeClick(breakfastModel: com.masai.foodvisor.model.BreakfastModel)
    fun onLunchRecipeClick(lunchModel: com.masai.foodvisor.model.LunchModel)
    fun onDinnerRecipeClick(dinnerModel: com.masai.foodvisor.model.DinnerModel)
    fun onDrinksRecipeClick(drinksModel: com.masai.foodvisor.model.DrinksModel)
}
