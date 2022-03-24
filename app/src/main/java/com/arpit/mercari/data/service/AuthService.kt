package com.arpit.mercari.data.service

import com.arpit.mercari.data.model.DoctorSignUpRequest
import com.arpit.mercari.data.model.Patient
import com.arpit.mercari.data.model.PatientSignUpRequest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface AuthService {

    @POST("patient/signup")
    suspend fun registerPatient(@Body patientSignUpRequest: PatientSignUpRequest)

    @POST("doctor")
    suspend fun registerDoctor(@Body doctorSignUpRequest: DoctorSignUpRequest)

    @GET("patient/profile")
    suspend fun loginPatient(
        @Header("token") token: String
    ): Patient

    @GET("/doctor/{id}")
    suspend fun loginDoctor(
        @Path("id") id: String
    )

}

val retrofit: Retrofit by lazy {
    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
    Retrofit.Builder()
//        .baseUrl("https://0779-171-76-254-99.ngrok.io/")
        .baseUrl("https://6922-171-76-254-99.ngrok.io/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
}

val authService: AuthService by lazy { retrofit.create(AuthService::class.java) }