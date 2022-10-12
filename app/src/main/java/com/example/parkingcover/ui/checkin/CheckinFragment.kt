package com.example.parkingcover.ui.checkin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parkingcover.R
import com.example.parkingcover.databinding.FragmentCheckinBinding
import com.example.parkingcover.utils.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckinFragment : Fragment() {

    private var _binding: FragmentCheckinBinding? = null
    private val binding get() = _binding!!
    private lateinit var checkinViewModel: CheckinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         checkinViewModel =
             ViewModelProvider(this)[CheckinViewModel::class.java]

        _binding = FragmentCheckinBinding.inflate(inflater, container, false)
        listenEvents()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        checkinViewModel.status.observe(viewLifecycleOwner){ status ->
            when(status){
                is ResponseStatus.Error -> {
                    binding.pbCheckin.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ResponseStatus.Loading -> binding.pbCheckin.visibility = View.VISIBLE
                is ResponseStatus.Success -> {
                    Toast.makeText(context, R.string.log_insert_success, Toast.LENGTH_LONG).show()
                    binding.pbCheckin.visibility = View.GONE
                }
            }
        }
    }

    private fun listenEvents() {
        binding.btnCheckinSend.setOnClickListener {
            checkinViewModel.isCarRegistered(
                binding.etvCheckinUserId.text.toString(),
                binding.etvCheckinUserName.text.toString(),
                binding.etvCheckinVehicleId.text.toString(),
                binding.etvCheckinTimeIn.text.toString().toInt(),
                null
            )
        }
    }

    private fun showErrorDialog(messageId: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_message)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ -> /** Dissmiss dialog **/ }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}