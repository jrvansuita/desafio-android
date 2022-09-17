package com.picpay.domain.usecases

import com.picpay.domain.models.User
import com.picpay.domain.repositories.UserRepository
import javax.inject.Inject

class FindAllUsersUseCase @Inject constructor(private val repository: UserRepository) :
    SingleUseCase<List<User>> {

    override fun execute() = repository.findAll()
}