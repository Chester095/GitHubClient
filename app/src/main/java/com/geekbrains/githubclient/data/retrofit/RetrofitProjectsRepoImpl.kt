package com.geekbrains.githubclient.data.retrofit

import com.geekbrains.githubclient.domain.Contact
import com.geekbrains.githubclient.domain.ProjectsRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitProjectsRepoImpl : ProjectsRepo {
    // 01:25:20 retrofit должен сконвертировать это в RX

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)

    override fun observeReposForUser(username: String): Single<List<Contact>> {
        return api.listRepos(username)
    }

}