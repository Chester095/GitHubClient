package com.geekbrains.githubclient.domain.usecase

import androidx.annotation.MainThread
import com.geekbrains.githubclient.domain.entities.Account
import com.geekbrains.githubclient.utils.CallbackData

interface ForgetPasswordUseCase {
    fun forgetPassword(
        email: String,
        @MainThread callback: CallbackData<Account>
    )
}