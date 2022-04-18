package com.geekbrains.githubclient.data.use_case

import android.os.Handler
import androidx.annotation.MainThread
import com.geekbrains.githubclient.domain.entities.Account
import com.geekbrains.githubclient.domain.ILoginApi
import com.geekbrains.githubclient.domain.usecase.LoginUseCase
import com.geekbrains.githubclient.utils.CallbackData

class LoginDataSource(
    private val api: ILoginApi,
    private val uiHandler: Handler
) : LoginUseCase {
    override fun login(
        login: String,
        password: String,
        @MainThread callback: CallbackData<Account>
    ) {
        Thread {
            try {
                val account = api.login(login, password)
                uiHandler.post {
                    callback.onSuccess(account)
                }
            } catch (exc: Exception) {
                uiHandler.post { callback.onError(exc) }
            }
        }.start()
    }
}