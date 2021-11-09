package com.masai.myjournalapp.Repository

import androidx.lifecycle.LiveData
import com.masai.myjournalapp.Model.FoodModel
import com.masai.myjournalapp.RoomDatabase.FoodDAO
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

    fun removeFood(foodModel: FoodModel) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDAO.removeFood(foodModel)
        }
    }

}