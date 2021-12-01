package com.example.testoemm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoordDao {
    @Insert
    suspend fun insertCoord(coord: Coord)

    @Query("SELECT * FROM coord ORDER BY id ASC")
    suspend fun getCoordList(): List<Coord>
}