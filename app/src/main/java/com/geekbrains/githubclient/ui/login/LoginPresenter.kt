package com.geekbrains.githubclient.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geekbrains.githubclient.domain.GitProjectEntity
import com.geekbrains.githubclient.domain.ProjectsRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class LoginPresenter(_view: LoginContract.View, private val gitProjectRepo: ProjectsRepo) : LoginContract.Presenter {

    private var view: LoginContract.View = _view
    private var adapterLogin = LoginRecyclingAdapter()

    private val _repos = MutableLiveData<List<GitProjectEntity>>()
    val repos: LiveData<List<GitProjectEntity>> = _repos
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        view.initViews(adapterLogin)
    }

    override fun onOpenLogin(gitProject: GitProjectEntity) {
            // подписываемся, получаем результат и отправляем
            compositeDisposable.add(
                gitProjectRepo
                    .observeReposForUser(gitProject.id.toString())
                    // подписались
                    .subscribeBy {
                        // пихаем в repos данные (список)
                        // postValue - потокобезопасный метод
                        _repos.postValue(it)
                    }
            )

    }

}