package com.masai.myjournalapp.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masai.myjournalapp.Model.FoodModel

@Database(entities = [FoodModel::class], version = 1)
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