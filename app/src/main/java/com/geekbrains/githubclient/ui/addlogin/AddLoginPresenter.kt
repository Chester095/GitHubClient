package com.geekbrains.githubclient.ui.addlogin

import android.content.Context
import android.util.Log
import com.geekbrains.githubclient.domain.Contact
import com.geekbrains.githubclient.data.DataHandlerLogin

class AddLoginPresenter(_view: AddLoginContract.View, context: Context) : AddLoginContract.Presenter {

    private var dataHandler = DataHandlerLogin(context)

    override fun onSaveLogin(contact: Contact) {
        dataHandler.insertContact(contact)
    }

    override fun onCancel() {
        Log.d("!!!", "onCancel addLogin")
    }


    override val itemCount: Int
        get() = dataHandler.getAllContacts().size

}