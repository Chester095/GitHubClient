package com.geekbrains.githubclient

import android.app.Application
import android.content.Context
import com.geekbrains.githubclient.data.retrofit.RetrofitProjectsRepoImpl
import com.geekbrains.githubclient.domain.ProjectsRepo

class App : Application() {
    val gitProjectsRepo: ProjectsRepo by lazy { RetrofitProjectsRepoImpl() }
}

// способ получить Application, нужное поле, нужного типа
val Context.app: App
    get() = applicationContext as App