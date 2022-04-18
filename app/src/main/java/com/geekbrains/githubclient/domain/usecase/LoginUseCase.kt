package com.geekbrains.githubclient.domain.usecase

import androidx.annotation.MainThread
import com.geekbrains.githubclient.domain.entities.Account
import com.geekbrains.githubclient.utils.CallbackData

interface LoginUseCase {
    fun login(
        login: String,
        password: String,
        @MainThread callback: CallbackData<Account>
    )
}