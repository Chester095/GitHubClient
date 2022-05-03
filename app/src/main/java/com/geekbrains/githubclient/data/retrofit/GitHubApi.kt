package com.geekbrains.githubclient.data.retrofit

import com.geekbrains.githubclient.domain.Contact
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

// 01:27:00
interface GitHubApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<Contact>>
}