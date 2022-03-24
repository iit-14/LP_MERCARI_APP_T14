package com.arpit.mercari.data.model

data class Bill(
    val appointmentId: Long,
    val medication: ArrayList<Medicine>,
    val total: Int
)
