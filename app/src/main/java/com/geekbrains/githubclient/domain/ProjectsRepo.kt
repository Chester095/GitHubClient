package com.geekbrains.githubclient.domain

import android.content.Context
import io.reactivex.rxjava3.core.Single

//  репозиторий с проектами
interface ProjectsRepo {
    // C(R)UD
    // получать данные по имени пользователя
    fun getProjectsFromServer(username: String): Single<List<GitProjectEntity>>

    fun getUsersFromLocalStorage(context: Context): List<Login>

    fun getUsersFromLocalDB(): List<Login>
}