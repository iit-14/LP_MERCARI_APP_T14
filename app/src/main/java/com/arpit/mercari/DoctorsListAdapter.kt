package com.arpit.mercari

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.mercari.data.model.Doctor
import com.arpit.mercari.databinding.DoctorCardItemBinding
import com.arpit.mercari.databinding.FragmentDoctorsListBinding

class DoctorsListAdapter(
    val doctors:List<Doctor>
) : RecyclerView.Adapter<DoctorsListAdapter.ViewHolder>() {
    lateinit var binding: DoctorCardItemBinding


    class ViewHolder(binding: DoctorCardItemBinding):RecyclerView.ViewHolder(binding.root){
        val name = binding.name
        val profession = binding.profession
        val exprience = binding.experience
        val degree = binding.degree
        val number = binding.number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DoctorCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = doctors[position].name
        holder.profession.text = doctors[position].speciality
        holder.degree.text = doctors[position].degree
        holder.exprience.text = doctors[position].experience.toString()
        holder.number.text = doctors[position].number.toString()
    }

    override fun getItemCount(): Int {
        return 3
    }

}