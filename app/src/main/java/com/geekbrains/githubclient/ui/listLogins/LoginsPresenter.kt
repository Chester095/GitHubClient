package com.geekbrains.githubclient.ui.listLogins

import android.content.Context
import com.geekbrains.githubclient.domain.Contact
import com.geekbrains.githubclient.data.DataHandlerLogin

class LoginsPresenter(_view: LoginsContract.View, context: Context) : LoginsContract.Presenter {

    private var view: LoginsContract.View = _view
    private var dataHandler = DataHandlerLogin(context)
    private var adapterMain: LoginsRecyclingAdapter = LoginsRecyclingAdapter(this)

    init {
        view.initViews(adapterMain)
    }

    override fun insertLogin(contact: Contact) {
        dataHandler.insertContact(contact)
        adapterMain.reLoad()
    }

    override fun updateContact(contact: Contact) {
        dataHandler.updateContact(contact)
        adapterMain.reLoad()
    }

    override fun deleteContact(contact: Contact) {
        dataHandler.deleteContact(contact)
        adapterMain.reLoad()
    }

    override fun getAllContacts(): List<Contact> {
        return dataHandler.getAllContacts()
    }

    override fun addButtonClicked() {
        view.showInsertLoginActivity()
    }

    // From recyclerview
    override val itemCount: Int
        get() = dataHandler.getAllContacts().size

    override fun onEditClicked(contact: Contact) {
        view.showEditLoginActivity(contact)
    }

    override fun onDeleteClicked(contact: Contact) {
        view.areYouSureAlertDialog(contact)
    }

    override fun onItemClicked(contact: Contact) {
        view.showLoginActivity(contact)
    }

    override fun onBindItemView(itemView: LoginsRecyclingAdapter.LoginViewHolder, pos: Int) {
        itemView.bindItem(getAllContacts()[pos])
    }

}