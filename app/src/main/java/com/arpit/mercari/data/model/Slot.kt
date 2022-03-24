package com.arpit.mercari.data.model

import java.util.*

data class Slot (
    val docId: String,
    val start: Date,
    val end: Date,
    val capacity: Int,
    val available: Int
)