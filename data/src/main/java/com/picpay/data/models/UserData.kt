package com.picpay.data.models

import com.google.gson.annotations.SerializedName


data class UserData(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String,
)