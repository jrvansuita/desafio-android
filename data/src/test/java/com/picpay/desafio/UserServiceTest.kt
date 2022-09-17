package com.picpay.desafio

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.data.repository.UserRepositoryImpl
import com.picpay.data.service.UserService
import com.picpay.domain.models.User
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserServiceTest {

    private val api = mock<UserService>()
    private val repository = UserRepositoryImpl(api)

    @Test
    fun shouldNotFindAnyUsers() {
        // given
        val expectedUsers = Single.just(emptyList<User>())
        whenever(api.getUsers()).thenReturn(expectedUsers)

        //when
        val users = repository.findAll()

        // then
        assertTrue(users.blockingGet().isEmpty())
    }

    @Test
    fun shouldFindAUser() {
        // given
        val expectedUsers = Single.just(listOf(User(1, "test", "test", "img")))
        whenever(api.getUsers()).thenReturn(expectedUsers)

        //when
        val users = repository.findAll()

        // then
        assertTrue(users.blockingGet().isNotEmpty())
        assertEquals(users.blockingGet().size, expectedUsers.blockingGet().size)
        assertEquals(users.blockingGet().first(), expectedUsers.blockingGet().first())
    }
}