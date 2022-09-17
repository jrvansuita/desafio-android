package com.picpay.data.api

import com.picpay.domain.models.User
import io.reactivex.Single
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun getUsers(): Single<List<User>>
}