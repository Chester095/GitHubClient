package com.geekbrains.githubclient.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.githubclient.domain.ProjectsRepo


// метод для передачи аргумента
class ReposViewModelFactory(private val repo: ProjectsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReposViewModel(repo) as T
    }
}