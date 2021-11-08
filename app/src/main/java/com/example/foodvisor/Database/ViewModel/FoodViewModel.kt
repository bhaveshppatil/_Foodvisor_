package com.masai.myjournalapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masai.myjournalapp.Model.FoodModel
import com.masai.myjournalapp.Repository.FoodRepository

class FoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    fun addFoodData(foodModel: FoodModel) {
        foodRepository.addFoodToRoom(foodModel)

    }

    fun getFood(): LiveData<List<FoodModel>> {
        return foodRepository.getAllFoodData()
    }
}