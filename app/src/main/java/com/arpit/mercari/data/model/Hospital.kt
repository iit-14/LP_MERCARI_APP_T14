package com.arpit.mercari.data.model

data class Hospital(
    val name: String,
    val address: String,
    val number: Int,
    val rating: Float,
    val doctors: ArrayList<Doctor>
)
