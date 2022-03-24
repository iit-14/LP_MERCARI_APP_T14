package com.arpit.mercari.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.arpit.mercari.R
import com.arpit.mercari.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenCreated {
            viewModel.homeScreenEvents.collect {
                val directions = HomeFragmentDirections.actionHomeFragmentToPreLoginFragment()
                findNavController().navigate(directions)
            }
        }
        binding.btnViewHospitals.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_hospitalListFragment)
        }
        binding.btnViewDoctors.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_doctorsListFragment)
        }
        binding.btnAppointment.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_appointmentFragment)
        }
        binding.btnRefer.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_referralFragment)
        }
    }

}