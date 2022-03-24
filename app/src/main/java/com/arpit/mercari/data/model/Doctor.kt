package com.arpit.mercari.data.model

data class Doctor(
    val id: Long,
    val tokenId: String,
    val name: String,
    val hospitalId: Long,
    val degree: String,
    val speciality: String,
    val number: Long,
    val experience: Int,
    val slots: ArrayList<Slot>
)
