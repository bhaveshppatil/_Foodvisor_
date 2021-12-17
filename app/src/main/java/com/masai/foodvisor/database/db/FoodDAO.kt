package com.masai.foodvisor.database.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masai.foodvisor.database.model.FoodModel

@Dao
interface FoodDAO {

    //Insert the data into database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRoutine(foodModel: FoodModel)

    //Inside we need to pass the query.
    @Query("select * from food_manager")
    fun getFoodData(): LiveData<List<FoodModel>>

    //Delete the record from Database
    @Delete
    fun removeFood(foodModel: FoodModel)

    @Query("SELECT * FROM food_manager WHERE name LIKE :search")
    fun getSearchData(search: String?): LiveData<List<FoodModel>>

    @Query("SELECT SUM(caloriesData) FROM food_manager")
    fun getCalData(): LiveData<Int>
}