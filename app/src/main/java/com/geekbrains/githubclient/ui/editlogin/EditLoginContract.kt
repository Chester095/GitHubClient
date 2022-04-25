package com.geekbrains.githubclient.ui.editlogin

import com.geekbrains.githubclient.ui.Contact

interface EditLoginContract {
    interface View {
        fun setSuccess()
        fun setError(error: String)
        fun setTextView()
    }

    interface Presenter {
        fun onCancel()
        fun updateContact(contact: Contact)

        val itemCount: Int
    }
}