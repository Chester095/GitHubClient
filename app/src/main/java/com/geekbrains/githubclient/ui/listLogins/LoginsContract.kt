package com.geekbrains.githubclient.ui.listLogins

import com.geekbrains.githubclient.domain.Contact

interface LoginsContract {
    interface View {
        fun initViews(adapterMain: LoginsRecyclingAdapter)
        fun showInsertLoginActivity()
        fun showEditLoginActivity(contact: Contact)
        fun showLoginActivity(contact: Contact)
        fun areYouSureAlertDialog(contact: Contact)
    }

    interface ItemView {
        fun bindItem(contact: Contact)
    }

    interface Presenter {
        fun addButtonClicked()
        fun insertLogin(contact: Contact)
        fun updateContact(contact: Contact)
        fun deleteContact(contact: Contact)
        fun getAllContacts(): List<Contact>

        val itemCount: Int
        fun onEditClicked(contact: Contact)
        fun onDeleteClicked(contact: Contact)
        fun onItemClicked(contact: Contact)
        fun onBindItemView(itemView: LoginsRecyclingAdapter.LoginViewHolder, pos: Int)
    }
}