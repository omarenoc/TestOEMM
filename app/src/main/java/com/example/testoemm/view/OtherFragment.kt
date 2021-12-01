package com.example.testoemm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testoemm.OemmApplication
import com.example.testoemm.R
import com.example.testoemm.adapters.OtherRecyclerViewAdapter
import com.example.testoemm.databinding.FragmentOtherBinding
import com.example.testoemm.databinding.FragmentOtherItemBinding
import com.example.testoemm.viewmodel.AppViewModel
import com.example.testoemm.viewmodel.AppViewModelFactory

class OtherFragment : Fragment() {

    private var _binding: FragmentOtherBinding? = null
    private val binding get() = _binding!!


    private val viewModel: AppViewModel by activityViewModels {
        AppViewModelFactory(
            (activity?.application as OemmApplication)
                .database.coordDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Commented due connection error
        // val myDataset = viewModel.retrieveCoordsList()
        //binding.list.adapter = OtherRecyclerViewAdapter(myDataset)
        //binding.list.layoutManager = LinearLayoutManager(this.context)
    }

}