package com.geekbrains.githubclient.ui.listLogins

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubclient.App
import com.geekbrains.githubclient.domain.GitProjectEntity
import com.geekbrains.githubclient.domain.ProjectsRepo
import com.geekbrains.githubclient.utils.AppState
import com.geekbrains.githubclient.utils.BaseViewModel

class LoginsViewModel(override val id: String) :
    ViewModel(),
    LoginsContracts.ViewModelContract,
    BaseViewModel {

    // список репозиториев
    private val _repos = MutableLiveData<List<GitProjectEntity>>()

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val repos: LiveData<List<GitProjectEntity>> = _repos

    private val repo: ProjectsRepo = App().gitProjectsRepo

    fun getData(): LiveData<AppState> = liveDataToObserve

    override fun getLogin(context:Context) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val user = repo.getUsersFromLocalStorage(context)
            liveDataToObserve.postValue(AppState.Success(user))
        }.start()
    }

}