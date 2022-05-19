package com.geekbrains.githubclient.ui.openLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.githubclient.domain.GitProjectEntity
import com.geekbrains.githubclient.domain.ProjectsRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ReposViewModel(private val gitProjectRepo: ProjectsRepo) : ViewModel() {
    // список репозиториев
    private val _repos = MutableLiveData<List<GitProjectEntity>>()
    val repos: LiveData<List<GitProjectEntity>> = _repos

    // MutableLiveData - является расширением LiveData
    // inProgress для демонстрации прогресса
    private val _inProgress = MutableLiveData<Boolean>()
    // LiveData - скрывает данные от изменений. только получать можно
    val inProgress: LiveData<Boolean> = _inProgress


    // метод отписки. чтобы от всех отписаться
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onShowRepos(username: String) {
        // когда начинается загрузка true
        _inProgress.postValue(true)

        // подписываемся, получаем результат и отправляем
        compositeDisposable.add(
            gitProjectRepo
                .observeReposForUser(username)
                // подписались
                .subscribeBy {
                    // когда закончилась загрузка false
                    _inProgress.postValue(false)
                    // пихаем в repos данные (список)
                    // postValue - потокобезопасный метод
                    _repos.postValue(it)
                }
        )
    }

    // отписаться
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}