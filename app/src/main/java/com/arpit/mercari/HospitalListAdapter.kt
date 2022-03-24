package com.arpit.mercari

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arpit.mercari.data.model.Hospital
import com.arpit.mercari.databinding.HospitalCardItemBinding

class HospitalListAdapter(
    val hospitals:List<Hospital>
) : RecyclerView.Adapter<HospitalListAdapter.ViewHolder>() {
    lateinit var binding:HospitalCardItemBinding
    class ViewHolder(binding: HospitalCardItemBinding): RecyclerView.ViewHolder(binding.root){
        val name = binding.name
        val address = binding.address
        val rating = binding.rating
        val phone = binding.number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HospitalCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = hospitals[position].name
        holder.address.text = hospitals[position].address
        holder.rating.text = hospitals[position].rating.toString()
        holder.phone.text = hospitals[position].number.toString()
    }

    override fun getItemCount(): Int {
        return hospitals.size
    }
}