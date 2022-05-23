package com.geekbrains.githubclient.ui.addLogin

import android.content.Context
import android.util.Log
import com.geekbrains.githubclient.domain.Login
import com.geekbrains.githubclient.data.DataHandlerLogin

class AddLoginPresenter(_view: AddLoginContract.View, context: Context) : AddLoginContract.Presenter {

    private var dataHandler = DataHandlerLogin()

    override fun onSaveLogin(login: Login) {
        dataHandler.insertContact(login)
    }

    override fun onCancel() {
        Log.d("!!!", "onCancel addLogin")
    }


    override val itemCount: Int
        get() = dataHandler.getAllContacts().size

}