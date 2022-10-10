package com.example.parkingcover.ui.carlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingcover.data.models.ParkingSlotsLog
import com.example.parkingcover.databinding.ItemCarListBinding

class CarListAdapter(val carList: List<ParkingSlotsLog>?) :
    RecyclerView.Adapter<CarListAdapter.CarListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListViewHolder {
        return CarListViewHolder(
            ItemCarListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarListViewHolder, position: Int) {
        val car = carList?.get(position)
        holder.binding.tvCarListVehicleId.text = car?.licensePlate
        holder.binding.tvCarListCarOwner.text = car?.userName
        holder.binding.tvCarListTimeIn.text = car?.timeIn.toString()

    }

    override fun getItemCount() = carList?.size ?: 0

    inner class CarListViewHolder(val binding: ItemCarListBinding) :
        RecyclerView.ViewHolder(binding.root)
}