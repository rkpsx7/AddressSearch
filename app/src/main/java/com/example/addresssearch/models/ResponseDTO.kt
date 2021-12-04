package com.example.addresssearch.models


import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("requestId")
    val requestId: String
)