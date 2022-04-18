package com.geekbrains.githubclient.data.use_case

import android.os.Handler
import androidx.annotation.MainThread
import com.geekbrains.githubclient.domain.entities.Account
import com.geekbrains.githubclient.domain.ILoginApi
import com.geekbrains.githubclient.domain.usecase.RegistrationUseCase
import com.geekbrains.githubclient.utils.CallbackData

class RegistrationDataSource(
    private val api: ILoginApi,
    private val uiHandler: Handler
) : RegistrationUseCase {
    override fun register(
        login: String,
        password: String,
        email: String,
        @MainThread callback: CallbackData<Account>
    ) {
        Thread {
            try {
                val account = api.register(login, password, email)
                uiHandler.post {
                    callback.onSuccess(account)
                }
            } catch (exc: Exception) {
                uiHandler.post { callback.onError(exc) }
            }
        }.start()
    }
}