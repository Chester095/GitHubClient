package com.geekbrains.githubclient.ui.login

import android.content.Context
import android.util.Log
import com.geekbrains.githubclient.domain.Contact
import com.geekbrains.githubclient.data.DataHandler
import com.geekbrains.githubclient.ui.addlogin.AddLoginContract

class LoginPresenter(_view: LoginContract.View, context: Context) : LoginContract.Presenter {

    private var dataHandler = DataHandler(context)

    override fun onOpenLogin(contact: Contact) {
        TODO("Not yet implemented")
    }

}