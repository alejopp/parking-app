package com.example.parkingcover.ui.carlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingcover.databinding.ItemCarListBinding

class CarListAdapter(val carList: List<String>) :
    RecyclerView.Adapter<CarListAdapter.CarListViewHolder>() {

    inner class CarListViewHolder(val binding: ItemCarListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListViewHolder {
        return CarListViewHolder(
            ItemCarListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = carList.size
}