package com.example.testoemm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testoemm.R
import com.example.testoemm.data.local.Coord
import com.example.testoemm.databinding.FragmentOtherItemBinding

class OtherRecyclerViewAdapter(
    private val dataset: List<Coord>
    ): RecyclerView.Adapter<OtherRecyclerViewAdapter.OtherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherViewHolder {
        return OtherViewHolder(
            FragmentOtherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OtherViewHolder, position: Int) {
        val item = dataset[position]
        holder.idView.text = item.id.toString()
        holder.latView.text = item.lat.toString()
        holder.lonView.text = item.lon.toString()
    }

    override fun getItemCount()= dataset.size

    class OtherViewHolder(binding: FragmentOtherItemBinding): RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.idPlaceholder
        val latView: TextView = binding.latPlaceholder
        val lonView: TextView = binding.lonPlaceholder
    }
}