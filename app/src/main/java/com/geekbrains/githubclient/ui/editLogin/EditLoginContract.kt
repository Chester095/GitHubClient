package com.geekbrains.githubclient.ui.editLogin

import com.geekbrains.githubclient.domain.Login

interface EditLoginContract {
    interface View {
        fun setSuccess(contactId: Int)
        fun setError(error: String)
        fun setTextView(contactLogin: String?)
    }

    interface Presenter {
        fun onCancel()
        fun updateContact(login: Login)

        val itemCount: Int
    }
}