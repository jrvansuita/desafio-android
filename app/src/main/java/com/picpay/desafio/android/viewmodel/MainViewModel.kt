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

    private val userLiveData = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = userLiveData
    private val compositeDisposable = CompositeDisposable()

    fun getUsers() {
        findAllUsersUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    userLiveData.postValue(it)
                }
            }, {
//Tratar o erro
            }).let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

}