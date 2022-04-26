package com.geekbrains.githubclient.ui.login

import com.geekbrains.githubclient.domain.Contact

interface LoginContract {
    interface View {
        fun setSuccess()
        fun setError(error: String)
        fun setTextView(contactLogin: String?)
    }

    interface Presenter {
        fun onOpenLogin(contact: Contact)
    }
}