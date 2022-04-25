package com.geekbrains.githubclient.ui.editlogin

import com.geekbrains.githubclient.ui.Contact

interface EditLoginContract {
    interface View {
        fun setSuccess(contactId: Int)
        fun setError(error: String)
        fun setTextView(contactLogin: String?)
    }

    interface Presenter {
        fun onCancel()
        fun updateContact(contact: Contact)

        val itemCount: Int
    }
}