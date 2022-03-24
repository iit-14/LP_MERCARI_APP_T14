package com.arpit.mercari.data.service

import com.arpit.mercari.data.model.Doctor
import com.arpit.mercari.data.model.Hospital
import com.arpit.mercari.data.model.Patient
import retrofit2.Response
import retrofit2.http.GET

interface ProfileService {
    @GET("")
    suspend fun getPatientProfile(id:Int) : Response<Patient>

    @GET("")
    suspend fun getDoctorProfile(id:Int) : Response<Doctor>

    @GET("")
    suspend fun getHospitalProfile(id:Int) : Response<Hospital>
}