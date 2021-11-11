package com.masai.myjournalapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "food_manager")
data class FoodModel(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "calories") var calories: String,
    @ColumnInfo(name = "caloriesData") var caloriesData: Int,
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

}