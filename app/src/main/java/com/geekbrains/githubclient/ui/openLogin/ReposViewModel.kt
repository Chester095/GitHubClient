package com.geekbrains.githubclient.ui.openLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubclient.domain.ProjectsRepo
import com.geekbrains.githubclient.utils.AppState
import com.geekbrains.githubclient.utils.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ReposViewModel(override val id: String, private val repository: ProjectsRepo) : ViewModel(), LoginContracts.ViewModelContract, BaseViewModel {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repo: ProjectsRepo = repository

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getData(): LiveData<AppState> = liveDataToObserve

    override fun getProjectsRetrofit(name: String) {
        compositeDisposable.add(
            repo
                .getProjectsFromServer(name)
                .subscribeBy {
                    liveDataToObserve.postValue(AppState.Success(it))
                }
        )
    }
}