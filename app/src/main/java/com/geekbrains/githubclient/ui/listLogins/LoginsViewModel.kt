package com.geekbrains.githubclient.ui.listLogins

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubclient.domain.ProjectsRepo
import com.geekbrains.githubclient.utils.AppState
import com.geekbrains.githubclient.utils.BaseViewModel

class LoginsViewModel(override val id: String, private val repository: ProjectsRepo) :
    ViewModel(),
    LoginsContracts.ViewModelContract,
    BaseViewModel {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repo: ProjectsRepo = repository

    fun getData(): LiveData<AppState> = liveDataToObserve

    override fun getLogin(context:Context) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val user = repo.getUsersFromLocalDB()
            liveDataToObserve.postValue(AppState.Success(user))
        }.start()
    }

}