package com.masai.foodvisor.database.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masai.foodvisor.database.model.FoodModel
import com.masai.foodvisor.database.repository.FoodRepository

class FoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    fun addFoodData(foodModel: FoodModel) {
        foodRepository.addFoodToRoom(foodModel)

    }

    fun getFood(): LiveData<List<FoodModel>> {
        return foodRepository.getAllFoodData()
    }

    fun getSearchFood(search: String): LiveData<List<FoodModel>> {
        return foodRepository.getFoodData(search)
    }

    fun getCaloriesData(): LiveData<Int> {
        return foodRepository.getCalData()
    }

    fun removeFood(foodModel: FoodModel) {
        foodRepository.removeFood(foodModel)
    }
}