package com.example.ghtk_4.model


import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("is_up")
    val isUp: Boolean,
    @SerializedName("title")
    val title: String
)