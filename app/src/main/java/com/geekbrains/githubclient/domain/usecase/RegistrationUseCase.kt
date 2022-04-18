package com.geekbrains.githubclient.domain.usecase

import androidx.annotation.MainThread
import com.geekbrains.githubclient.domain.entities.Account
import com.geekbrains.githubclient.utils.CallbackData

interface RegistrationUseCase {
    fun register(
        login: String,
        password: String,
        email: String,
        @MainThread callback: CallbackData<Account>
    )
}