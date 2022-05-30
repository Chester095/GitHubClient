package com.geekbrains.githubclient.data

import com.geekbrains.githubclient.data.retrofit.GitHubApi
import com.geekbrains.githubclient.data.retrofit.ProjectsRepoImpl
import com.geekbrains.githubclient.domain.ProjectsRepo
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module{
    single<String>(named("api_github")) {"https://api.github.com/"}
    single<ProjectsRepo> {ProjectsRepoImpl(get<GitHubApi>())}
    single<GitHubApi> {get<Retrofit>().create(GitHubApi::class.java)  }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named("api_github")))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(get())
            .build()
    }
    factory<Converter.Factory> { GsonConverterFactory.create() }
}