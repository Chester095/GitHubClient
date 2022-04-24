package com.geekbrains.githubclient.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.ItemLoginBinding
import com.geekbrains.githubclient.ui.main.MainContract

class RecyclingAdapter(private val presenter: MainContract.Presenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemLoginBinding

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        MainContract.ItemView {

        private val loginTextView = binding.loginTextView
        private val imageViewEdit = binding.imageViewEdit
        private val imageViewDelete = binding.imageViewDelete
        private val container = binding.mainContainer

        private lateinit var currentContact: Contact

        init {
            imageViewEdit.setOnClickListener {
                presenter.onEditClicked(currentContact)
            }
            imageViewDelete.setOnClickListener {
                presenter.onDeleteClicked(currentContact)
            }
        }

        override fun bindItem(contact: Contact) {
            currentContact = contact
            loginTextView.text = contact.login

            if (adapterPosition % 2 == 0) {
                container.setBackgroundResource(R.color.white)
            } else {
                container.setBackgroundResource(R.color.purple_200)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_login, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolder) {
            presenter.onBindItemView(holder, position)
        }
    }

    override fun getItemCount(): Int = presenter.itemCount

    fun reLoad() {
        notifyDataSetChanged()
    }
}