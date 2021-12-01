package com.example.testoemm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Coord::class], version = 1, exportSchema = false)
abstract class CoordinatesDatabase: RoomDatabase() {
    abstract fun coordDao(): CoordDao

    companion object{
        @Volatile
        private var INSTANCE: CoordinatesDatabase? = null

        fun getDatabase(context: Context): CoordinatesDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoordinatesDatabase::class.java,
                    "coordinates_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}