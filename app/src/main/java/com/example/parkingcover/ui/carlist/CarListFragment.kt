package com.example.parkingcover.ui.carlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkingcover.databinding.FragmentCarListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarListFragment : Fragment() {

    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!
    private lateinit var carListViewModel: CarListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carListViewModel =
            ViewModelProvider(this)[CarListViewModel::class.java]
        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        initComponents()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        carListViewModel.carsInParking.observe(viewLifecycleOwner){ carList ->
            binding.rvCarlist.adapter = CarListAdapter(carList)
        }
    }

    private fun initComponents() {
        //Set recycler view
        binding.rvCarlist.layoutManager = LinearLayoutManager(context)
        //Get car list in
        carListViewModel.getCarsInParking()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}