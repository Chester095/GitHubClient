package com.geekbrains.githubclient.ui.addlogin

import android.view.View
import com.geekbrains.githubclient.ui.Contact
import com.geekbrains.githubclient.ui.RecyclingAdapter
import java.lang.Error

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