package com.geekbrains.githubclient.ui.main

import com.geekbrains.githubclient.ui.Contact
import com.geekbrains.githubclient.ui.RecyclingAdapter

interface MainContract {
    interface View {
        fun initViews(adapter: RecyclingAdapter)
        fun showInsertLoginActivity()
        fun showOpenLoginActivity(contact: Contact)
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
        fun onBindItemView(itemView: RecyclingAdapter.MyViewHolder, pos: Int)
    }
}