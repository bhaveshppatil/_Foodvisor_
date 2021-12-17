package com.masai.foodvisor.database.adapter

import com.masai.foodvisor.database.model.FoodModel

interface Clicklistener {

    fun onFoodRemoveClick(foodModel: FoodModel)
}