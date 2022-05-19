package com.geekbrains.githubclient.ui.listLogins

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.domain.Contact

class LoginsRecyclingAdapter(private val mainPresenter: LoginsContract.Presenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class LoginViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        LoginsContract.ItemView {

        private val loginTextView = itemView.findViewById<TextView>(R.id.login_text_view)
        private val imageViewEdit = itemView.findViewById<ImageView>(R.id.image_view_edit)
        private val imageViewDelete = itemView.findViewById<ImageView>(R.id.image_view_delete)

        private lateinit var currentContact: Contact

        init {
            imageViewEdit.setOnClickListener {
                mainPresenter.onEditClicked(currentContact)
            }
            imageViewDelete.setOnClickListener {
                mainPresenter.onDeleteClicked(currentContact)
            }
            itemView.setOnClickListener {
                mainPresenter.onItemClicked(currentContact)
            }

        }

        override fun bindItem(contact: Contact) {
            currentContact = contact
            loginTextView.text = contact.login
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = LayoutInflater.from(parent.context).inflate(R.layout.item_login, parent, false)
        return LoginViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoginViewHolder) {
            mainPresenter.onBindItemView(holder, position)
        }
    }

    override fun getItemCount(): Int = mainPresenter.itemCount

    fun reLoad() {
        notifyDataSetChanged()
    }
}