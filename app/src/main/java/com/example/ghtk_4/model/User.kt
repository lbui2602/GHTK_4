package com.example.ghtk_4.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("history")
    val history: List<History>,
    @SerializedName("message")
    val message: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("success")
    val success: Boolean
)