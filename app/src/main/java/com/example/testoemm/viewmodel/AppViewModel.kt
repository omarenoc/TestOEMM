package com.example.testoemm.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.testoemm.data.local.Coord
import com.example.testoemm.data.local.CoordDao
import com.example.testoemm.data.remote.MyApi
import com.example.testoemm.data.remote.MyResponse
import com.example.testoemm.data.remote.asDatabaseModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class AppViewModel(private val coordDao: CoordDao): ViewModel() {
    private val _coords = MutableLiveData<MyResponse>()

    val coords: LiveData<MyResponse> = _coords

    private val _roomCoordsList = MutableLiveData<List<Coord>>()

    val roomCoordsList: LiveData<List<Coord>> = _roomCoordsList

    // Asks for the data from the server
    private fun getMapsCoords() {
        viewModelScope.launch {
            try {
                _coords.value = MyApi.retrofitService.getCoords()
                addRoomCoords(coords)
            }
            catch (e: Exception) {
                Log.e("ViewModel", "Error de API: $e")
            }
        }
    }


    // Translate from MyResponse to Coord object and inserts it on the database
    private suspend fun addRoomCoords(coords: LiveData<MyResponse>) {
        val roomCoords = coords.value!!.asDatabaseModel()
        coordDao.insertCoord(roomCoords)
    }

    // Retrieves a list with all elements from database sorted by id
    private fun getCoordsList(): LiveData<List<Coord>> {
        viewModelScope.launch {
            _roomCoordsList.value = coordDao.getCoordList()
        }
        return roomCoordsList
    }

    // Method used to retrieve a list with all Coord elements to show in RecyclerView
    fun retrieveCoordsList(): List<Coord> {
        val requestedCoordsList = getCoordsList()
        return requestedCoordsList.value!!
    }

    // Used to trigger data reception from Retrieve Data button.
    fun retrieveData() {
        getMapsCoords()
    }

    // Method to get a LatLang object used to add a marker in the map.
    fun retrieveMapsCoordsFromApi(): LatLng {
        retrieveData()
        val mapsResponse = coords.value
        val mapsCoordinates = LatLng(mapsResponse!!.lat, mapsResponse.lon)
        return mapsCoordinates
    }




}

// ViewModelFactory
class AppViewModelFactory(private val coordDao: CoordDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(coordDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}