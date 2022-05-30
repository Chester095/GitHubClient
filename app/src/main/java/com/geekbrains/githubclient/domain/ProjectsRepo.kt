package com.geekbrains.githubclient.domain

import android.content.Context
import io.reactivex.rxjava3.core.Single

interface ProjectsRepo {

    fun getProjectsFromServer(username: String): Single<List<GitProjectEntity>>

    fun getUsersFromLocalStorage(context: Context): List<Login>

    fun getUsersFromLocalDB(): List<Login>
}