package com.arpit.mercari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpit.mercari.data.model.Doctor
import com.arpit.mercari.data.model.Hospital


class HospitalListFragment : Fragment() {
    private val hospitals: List<Hospital> = emptyList<Hospital>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hospital_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.hospital_list_recycler_view)
        val adapter = HospitalListAdapter(hospitals)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter =adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    fun fetchData(){
        // get data // update list // update adapter
    }
}