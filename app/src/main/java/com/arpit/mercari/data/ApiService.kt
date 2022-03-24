package com.arpit.mercari.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

val apiService: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}