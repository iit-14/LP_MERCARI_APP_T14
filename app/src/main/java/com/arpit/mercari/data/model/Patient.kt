package com.arpit.mercari.data.model

data class PatientSignUpRequest(
    val name: String,
    val nhid: Long,
    val gender: String,
    val age: Int,
    val address: String,
    val number: Long,
    val token: String,
)

data class DoctorSignUpRequest(
    val Name: String,
    val TokenID: String,
    val HospitalID: Long,
    val Degree: String,
    val Speciality: String,
    val Number: Long,
    val Experience: Long
)

data class Patient(
    val name: String,
    val nhid: Long,
    val gender: String,
    val age: Int,
    val address: String,
    val number: Long,
)