package com.picpay.data.service

import com.picpay.domain.models.User
import io.reactivex.Single
import retrofit2.http.GET

typealias Users = Single<List<User>>

interface UserService {
    @GET("users")
    fun getUsers(): Users
}