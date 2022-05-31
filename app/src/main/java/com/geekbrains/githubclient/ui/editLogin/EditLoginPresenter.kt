package com.geekbrains.githubclient.ui.editLogin

import android.content.Context
import android.util.Log
import com.geekbrains.githubclient.domain.Login
import com.geekbrains.githubclient.data.DataHandlerLogin

class EditLoginPresenter(_view: EditLoginContract.View, context: Context) : EditLoginContract.Presenter {

    private var dataHandler = DataHandlerLogin(context)


    override fun updateContact(login: Login) {
        dataHandler.updateContact(login)
    }

    override fun onCancel() {
        Log.d("!!!", "onCancel editLogin")
    }


    override val itemCount: Int
        get() = dataHandler.getAllContacts().size

}