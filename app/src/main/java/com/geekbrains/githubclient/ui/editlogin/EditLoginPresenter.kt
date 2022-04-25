package com.geekbrains.githubclient.ui.editlogin

import android.content.Context
import android.util.Log
import com.geekbrains.githubclient.ui.Contact
import com.geekbrains.githubclient.ui.DataHandler

class EditLoginPresenter(_view: EditLoginContract.View, context: Context) : EditLoginContract.Presenter {

    private var dataHandler = DataHandler(context)


    override fun updateContact(contact: Contact) {
        dataHandler.updateContact(contact)
    }

    override fun onCancel() {
        Log.d("!!!", "onCancel editLogin")
    }


    override val itemCount: Int
        get() = dataHandler.getAllContacts().size

}