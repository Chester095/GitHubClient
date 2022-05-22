package com.geekbrains.githubclient.ui.addLogin

import com.geekbrains.githubclient.domain.Login

interface AddLoginContract {
    interface View {
        fun setSuccess()
        fun setError(error: String)
    }

    interface Presenter {
        fun onSaveLogin(login: Login)
        fun onCancel()
        val itemCount: Int
    }
}