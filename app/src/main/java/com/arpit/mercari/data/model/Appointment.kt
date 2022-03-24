package com.arpit.mercari.data.model

import java.util.*

data class Appointment(
    val patient: Patient,
    val docId: Long,
    val symptoms: String,
    val slotStart: Long,
    val slotEnd: Long,
    val bill: Bill
)
