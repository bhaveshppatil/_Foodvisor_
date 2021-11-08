package com.masai.myjournalapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "food_manager")
data class FoodModel(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "decs") var decs: String,
    @ColumnInfo(name = "link") var link: String,

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

}