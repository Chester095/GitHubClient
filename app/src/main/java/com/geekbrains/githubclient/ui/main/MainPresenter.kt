package com.geekbrains.githubclient.ui.main

import android.content.Context
import com.geekbrains.githubclient.domain.Contact
import com.geekbrains.githubclient.data.DataHandler

class MainPresenter(_view: MainContract.View, context: Context) : MainContract.Presenter {

    private var view: MainContract.View = _view
    private var dataHandler = DataHandler(context)
    private var adapter: RecyclingAdapter = RecyclingAdapter(this)

    init {
        view.initViews(adapter)
    }

    override fun insertLogin(contact: Contact) {
        dataHandler.insertContact(contact)
        adapter.reLoad()
    }

    override fun updateContact(contact: Contact) {
        dataHandler.updateContact(contact)
        adapter.reLoad()
    }

    override fun deleteContact(contact: Contact) {
        dataHandler.deleteContact(contact)
        adapter.reLoad()
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

    override fun onBindItemView(itemView: RecyclingAdapter.MyViewHolder, pos: Int) {
        itemView.bindItem(getAllContacts()[pos])
    }

}