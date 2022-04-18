package com.geekbrains.githubclient.domain

import androidx.annotation.WorkerThread
import com.geekbrains.githubclient.domain.entities.Account

interface ILoginApi {

    @WorkerThread
    fun login(login: String, password: String) : Account

    @WorkerThread
    fun register(login: String, password: String, email: String) : Account

    @WorkerThread
    fun forgotPassword(email: String) : Account
}