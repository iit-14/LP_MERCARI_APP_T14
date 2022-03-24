package com.arpit.mercari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpit.mercari.data.model.Doctor
import com.arpit.mercari.databinding.FragmentHomeBinding

class DoctorsListFragment : Fragment() {
    private val doctors: List<Doctor> = emptyList<Doctor>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_doctors_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.doctor_list_recycler_view)
        val adapter = DoctorsListAdapter(doctors)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter =adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDoctors()
    }

    fun fetchDoctors(){
        // fetch data
        // update list
        // call adapter
    }
}
