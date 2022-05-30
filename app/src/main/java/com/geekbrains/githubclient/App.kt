package com.geekbrains.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.data.retrofit.ProjectsRepoImpl
import com.geekbrains.githubclient.domain.ProjectsRepo
import com.geekbrains.githubclient.utils.ViewModelStore

class App : Application() {
    val gitProjectsRepo: ProjectsRepo by lazy { ProjectsRepoImpl() }
    val viewModelStore by lazy { ViewModelStore() }
}

// способ получить Application, нужное поле, нужного типа
val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
