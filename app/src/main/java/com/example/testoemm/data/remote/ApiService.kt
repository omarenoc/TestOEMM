package com.example.testoemm.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private object Constants {
    const val BASE_URL =
        "https://community-open-weather-map.p.rapidapi.com/weather?q=London%2Cuk&lat=0&lon=0&callback=test&id=2172797&lang=null&units=%22metric%22%20or%20%22imperial%22&mode=xml%2C%20html"
    const val KEY = "x-rapidapi-key: d3fe3a34c5mshf70e1bb90c464a8p1ba4aajsn4233972e8a9c"
    const val HOST = "x-rapidapi-host: community-open-weather-map.p.rapidapi.com"
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()


interface ApiService {

    @Headers(
        Constants.KEY,
        Constants.HOST
    )
    @GET("/coord")
    suspend fun getCoords(): MyResponse
}

object MyApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}