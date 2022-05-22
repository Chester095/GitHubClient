package com.geekbrains.githubclient.ui.listLogins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.githubclient.domain.ProjectsRepo


// метод для передачи аргумента
class LoginsViewModelFactory(private val repo: ProjectsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginsViewModel(repo) as T
    }
}