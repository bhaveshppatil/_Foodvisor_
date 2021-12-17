package com.masai.foodvisor.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masai.foodvisor.database.model.FoodModel

@Database(entities = [FoodModel::class], version = 3)
abstract class FoodRoomDB : RoomDatabase() {

    abstract fun getFoodDAO(): FoodDAO

    companion object {
        private var INSTANCE: FoodRoomDB? = null

        fun getDatabaseObject(context: Context): FoodRoomDB {
            if (INSTANCE != null) {
                return INSTANCE!!
            } else {
                val builder = Room.databaseBuilder(
                    context.applicationContext, FoodRoomDB::class.java, "food.db"
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
            }
            return INSTANCE!!
        }
    }
}