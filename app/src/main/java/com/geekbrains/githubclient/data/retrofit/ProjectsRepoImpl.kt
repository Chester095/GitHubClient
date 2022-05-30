package com.geekbrains.githubclient.data.retrofit

import android.content.Context
import com.geekbrains.githubclient.data.DataHandlerLogin
import com.geekbrains.githubclient.data.db.Logins
import com.geekbrains.githubclient.domain.GitProjectEntity
import com.geekbrains.githubclient.domain.Login
import com.geekbrains.githubclient.domain.ProjectsRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.crypto.Cipher


class ProjectsRepoImpl(private val api: GitHubApi) : ProjectsRepo {

    override fun getProjectsFromServer(username: String): Single<List<GitProjectEntity>> {
        return api.listRepos(username)
    }

    override fun getUsersFromLocalStorage(context: Context): List<Login> {
        return DataHandlerLogin(context).getAllContacts()
    }

    override fun getUsersFromLocalDB(): List<Login> {
        return Logins().logins
    }


}