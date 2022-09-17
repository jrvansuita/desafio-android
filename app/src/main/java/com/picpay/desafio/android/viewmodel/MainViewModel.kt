package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.domain.models.User
import com.picpay.domain.usecases.FindAllUsersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val findAllUsersUseCase: FindAllUsersUseCase
) : ViewModel() {

    sealed class State {
        class Success(val users: List<User>) : State()
        class Error(val message: String?) : State()
    }

    private val mutableState = MutableLiveData<State>()
    val state: LiveData<State> = mutableState
    private val compositeDisposable = CompositeDisposable()

    fun getUsers() {
        findAllUsersUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    mutableState.postValue(State.Success(it))
                }
            }, {
                mutableState.postValue(State.Error(it.message))
            }).let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

}