package com.picpay.desafio

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.domain.models.User
import com.picpay.domain.repositories.UserRepository
import com.picpay.domain.usecases.FindAllUsersUseCase
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FindAllUsersUseCaseTest {

    private val repository = mock<UserRepository>()
    private val useCase = FindAllUsersUseCase(repository)

    @Test
    fun shouldNotFindAnyUsers() {
        // given
        val expectedUsers = Single.just(emptyList<User>())
        whenever(repository.findAll()).thenReturn(expectedUsers)

        //when
        val users = useCase.execute()

        // then
        assertTrue(users.blockingGet().isEmpty())
    }


    @Test
    fun shouldFindAUser() {
        // given
        val expectedUsers = Single.just(listOf(User(1, "test", "test", "img")))
        whenever(repository.findAll()).thenReturn(expectedUsers)

        //when
        val users = useCase.execute()

        // then
        assertTrue(users.blockingGet().isNotEmpty())
        assertEquals(users.blockingGet().size, expectedUsers.blockingGet().size)
        assertEquals(users.blockingGet().first(), expectedUsers.blockingGet().first())
    }
}