package com.geekbrains.githubclient.domain

import io.reactivex.rxjava3.core.Single

//  репозиторий с проектами
interface ProjectsRepo {
    // C(R)UD
    // получать данные по имени пользователя
    fun observeReposForUser(username: String): Single<List<GitProjectEntity>>
}