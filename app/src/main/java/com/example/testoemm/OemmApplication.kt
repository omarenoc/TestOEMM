package com.example.testoemm

import android.app.Application
import com.example.testoemm.data.local.CoordinatesDatabase

class OemmApplication: Application() {
    val database: CoordinatesDatabase by lazy { CoordinatesDatabase.getDatabase(this) }
}