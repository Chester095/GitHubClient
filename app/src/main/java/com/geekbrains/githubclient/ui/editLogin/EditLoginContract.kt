package com.geekbrains.githubclient.ui.editLogin

import com.geekbrains.githubclient.domain.Contact

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