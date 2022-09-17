package com.picpay.desafio.android

import com.picpay.domain.models.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}