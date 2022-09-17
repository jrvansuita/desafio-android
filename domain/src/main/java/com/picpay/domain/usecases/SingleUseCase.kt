package com.picpay.domain.usecases

import io.reactivex.Single

interface SingleUseCase<R> {
    fun execute(): Single<R>
}