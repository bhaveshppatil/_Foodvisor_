package com.masai.foodvisor.database.repository

import androidx.lifecycle.LiveData
import com.masai.foodvisor.database.model.FoodModel
import com.masai.foodvisor.database.db.FoodDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodRepository(val foodDAO: FoodDAO) {

    fun addFoodToRoom(foodModel: FoodModel) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDAO.addRoutine(foodModel)
        }
    }

    fun getAllFoodData(): LiveData<List<FoodModel>> {
        return foodDAO.getFoodData()
    }

    fun getFoodData(search: String): LiveData<List<FoodModel>> {
        return foodDAO.getSearchData(search)
    }
    fun getCalData(): LiveData<Int> {
        return foodDAO.getCalData()
    }

    fun removeFood(foodModel: FoodModel) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDAO.removeFood(foodModel)
        }
    }

}