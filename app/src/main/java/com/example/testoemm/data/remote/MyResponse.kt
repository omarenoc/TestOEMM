package com.example.testoemm.data.remote

import com.example.testoemm.data.local.Coord


data class MyResponse (
    var lon: Double,
    var lat: Double
)

fun MyResponse.asDatabaseModel(): Coord {
    return Coord(
        lat = lat,
        lon = lon
    )
}