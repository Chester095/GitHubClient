package com.geekbrains.githubclient.ui.listLogins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubclient.App
import com.geekbrains.githubclient.domain.GitProjectEntity
import com.geekbrains.githubclient.domain.ProjectsRepo
import com.geekbrains.githubclient.utils.AppState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class LoginsViewModel(private val gitProjectRepo: ProjectsRepo) : ViewModel() {
    // список репозиториев
    private val _repos = MutableLiveData<List<GitProjectEntity>>()
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val repos: LiveData<List<GitProjectEntity>> = _repos

    private val repo: ProjectsRepo = App().gitProjectsRepo

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getLogin() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val user = repo.getUsersFromLocalStorage()
            liveDataToObserve.postValue(AppState.Success(user))
        }.start()
    }

}