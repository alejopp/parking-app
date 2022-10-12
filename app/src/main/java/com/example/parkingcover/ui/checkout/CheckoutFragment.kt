package com.example.parkingcover.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parkingcover.R
import com.example.parkingcover.databinding.FragmentCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var checkoutViewModel: CheckoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        checkoutViewModel =
            ViewModelProvider(this).get(CheckoutViewModel::class.java)
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        setListeners()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        checkoutViewModel.totalTime.observe(viewLifecycleOwner) { totalTime ->
            binding.tvCheckoutTotalTime.text =
                getString(R.string.total_time, totalTime.first, totalTime.second)
        }
        checkoutViewModel.totalPrice.observe(viewLifecycleOwner) { totalPrice ->
            binding.tvCheckoutTotalPayment.text = getString(R.string.total_price, totalPrice)
        }
    }

    private fun setListeners() {
        binding.btCheckoutSend.setOnClickListener {
            checkoutViewModel.setTotalTimeAndTotalPrice(
                binding.etvCheckoutVehicleId.text.toString(),
                binding.etvCheckoutTimeOut.text.toString().toInt()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}