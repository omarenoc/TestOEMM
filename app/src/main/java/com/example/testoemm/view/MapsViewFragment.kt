package com.example.testoemm.view

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.testoemm.OemmApplication
import com.example.testoemm.R
import com.example.testoemm.databinding.FragmentMapsViewBinding
import com.example.testoemm.viewmodel.AppViewModel
import com.example.testoemm.viewmodel.AppViewModelFactory

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsViewFragment : Fragment() {

    private var _binding: FragmentMapsViewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AppViewModel by activityViewModels {
        AppViewModelFactory((activity?.application as OemmApplication).database.coordDao())
    }

    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(-34.0, 151.0)
        // Commented due connection problem:
        // var marker = viewModel.retrieveMapsCoordsFromApi()
        //  if (marker.latitude.isNaN() || marker.longitude.isNaN()) {
        //  marker = sydney
        //}
        val marker = sydney
        val zoomLevel = 10F
        googleMap.addMarker(MarkerOptions().position(marker).title("Marker"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, zoomLevel))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}