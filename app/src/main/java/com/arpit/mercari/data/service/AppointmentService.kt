package com.arpit.mercari.data.service

import com.arpit.mercari.data.model.Appointment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppointmentService {

    @GET("api/v2/entries/en/{word}")
    suspend fun getAppointments(@Path("word") word: String): List<Appointment>

    @POST("")
    suspend fun makeAppointment(@Path("word") word: String): Response<Boolean>

}