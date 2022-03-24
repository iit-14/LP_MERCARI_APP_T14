package com.arpit.mercari

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.arpit.mercari.data.model.Appointment
import com.arpit.mercari.databinding.FragmentAppointmentBinding


class AppointmentFragment : Fragment() {
    private var _binding: FragmentAppointmentBinding? =null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAppointmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appointmentDetails: Appointment
        binding.radioBtnHigh.isChecked=true
        binding.homeIcon.setOnClickListener {
//            ToDo: Navigate to home or remove the icon
        }
        val doctorList = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val hospitalList = listOf("Abc","Def","Ghi","Jkl")
            //ToDo get list of doctors and hospitals.
        val doctorListAdapter = ArrayAdapter(requireContext(), R.layout.doctor_list_item, doctorList)
        val hospitalListAdapter = ArrayAdapter(requireContext(),R.layout.hospital_list_item,hospitalList)
        (binding.selectHospital as? AutoCompleteTextView)?.setAdapter(hospitalListAdapter)
        (binding.selectDoctor as? AutoCompleteTextView)?.setAdapter(doctorListAdapter)

        binding.btnBook.setOnClickListener {
//            ToDo: Send appointment data
            val hospital = binding.selectHospital.text.toString()
            val doctor = binding.selectDoctor.text.toString()
            val isHighUrgency=binding.radioBtnHigh.isChecked
//            ToDo Urgency can be removed
            val symptoms = binding.briefSymptomsEditText.editableText.toString()
            if(hospital == "")
                binding.selectHospital.error = "Please select a hospital"
            else if(hospital!="")
                binding.selectHospital.error = null
            if(doctor == "")
                binding.selectDoctor.error = "Please select a doctor"
            else if(doctor!="")
                binding.selectDoctor.error = null
            if(symptoms == "")
                binding.briefSymptoms.error = "Please describe the symptoms"
            else if(symptoms!="")
                binding.briefSymptoms.error = null
            if(hospital!="" && doctor!="" && symptoms!=""){
                findNavController().navigate(R.id.action_appointmentFragment_to_slotFragment)

            }
        }
    }

}