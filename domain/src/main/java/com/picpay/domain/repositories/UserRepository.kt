package com.picpay.domain.repositories

import com.picpay.domain.models.User
import io.reactivex.Single

interface UserRepository {
    fun findAll(): Single<List<User>>
}