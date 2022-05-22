package com.geekbrains.githubclient.ui.listLogins

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.databinding.ItemLoginBinding
import com.geekbrains.githubclient.domain.Login

class LoginsViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = ItemLoginBinding.bind(itemView)

    companion object {
        @JvmStatic
        fun createView(parent: ViewGroup): LoginsViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_login, parent, false)
            return LoginsViewHolder(view)
        }
    }

    fun bind(login: Login, listener: (Login) -> Unit) {
        binding.loginTextView.text = login.login
        binding.root.setOnClickListener {
            listener.invoke(login)
        }
        binding.imageViewDelete.setOnClickListener {
            listener.invoke(login)
        }
        binding.imageViewEdit.setOnClickListener {
            listener.invoke(login)
        }
    }

/*    inner class LoginViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        LoginsContract.ItemView {

        private val loginTextView = itemView.findViewById<TextView>(R.id.login_text_view)
        private val imageViewEdit = itemView.findViewById<ImageView>(R.id.image_view_edit)
        private val imageViewDelete = itemView.findViewById<ImageView>(R.id.image_view_delete)

        private lateinit var currentLogin: Login

        init {
            imageViewEdit.setOnClickListener {
                mainPresenter.onEditClicked(currentLogin)
            }
            imageViewDelete.setOnClickListener {
                mainPresenter.onDeleteClicked(currentLogin)
            }
            itemView.setOnClickListener {
                mainPresenter.onItemClicked(currentLogin)
            }

        }

        fun bindItem(login: Login) {
            currentLogin = login
            loginTextView.text = login.login
        }

    }*/
}