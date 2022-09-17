package com.picpay.data.repository

import com.picpay.data.api.UserService
import com.picpay.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val service: UserService) : UserRepository {

    override fun findAll() = service.getUsers()

}