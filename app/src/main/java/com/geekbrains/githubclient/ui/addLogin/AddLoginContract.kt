package com.geekbrains.githubclient.ui.addLogin

import com.geekbrains.githubclient.domain.Contact

interface AddLoginContract {
    interface View {
        fun setSuccess()
        fun setError(error: String)
    }

    interface Presenter {
        fun onSaveLogin(contact: Contact)
        fun onCancel()
        val itemCount: Int
    }
}