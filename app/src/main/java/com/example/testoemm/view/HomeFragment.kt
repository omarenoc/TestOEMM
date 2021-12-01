package com.example.testoemm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.testoemm.OemmApplication
import com.example.testoemm.databinding.FragmentHomeBinding
import com.example.testoemm.viewmodel.AppViewModel
import com.example.testoemm.viewmodel.AppViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AppViewModel by activityViewModels {
        AppViewModelFactory((activity?.application as OemmApplication).database.coordDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.mapsViewButton.setOnClickListener { navigateMapsViewFragment() }
        binding.otherButton.setOnClickListener { navigateOtherFragment() }
        //binding.retrieveButton.setOnClickListener { retrieveData() }
        return binding.root
    }


    private fun navigateOtherFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToOtherFragment()
        findNavController().navigate(action)
    }

    private fun navigateMapsViewFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToMapsViewFragment()
        findNavController().navigate(action)
    }

    // Adds functionality to the retrieveButton
    private fun retrieveData() {
        viewModel.retrieveData()
    }
}