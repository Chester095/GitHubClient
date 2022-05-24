package com.geekbrains.githubclient.ui.openLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubclient.App
import com.geekbrains.githubclient.domain.GitProjectEntity
import com.geekbrains.githubclient.domain.ProjectsRepo
import com.geekbrains.githubclient.utils.AppState
import com.geekbrains.githubclient.utils.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ReposViewModel(override val id: String) : ViewModel(), CardContracts.ViewModelContract, BaseViewModel {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repo: ProjectsRepo = App().gitProjectsRepo

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

    override fun getProjects(name: String) {
        liveDataToObserve.value = AppState.Loading
//        val project = repo.getPojectsUsersFromLocalStorage(name)
//        liveDataToObserve.postValue(AppState.Success(project))
    }
}