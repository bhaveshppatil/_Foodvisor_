package com.masai.myjournalapp.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masai.myjournalapp.Model.FoodModel

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
}