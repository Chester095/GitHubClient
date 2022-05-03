package com.geekbrains.githubclient.ui.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.domain.GitProjects

class LoginRecyclingAdapter(private val presenter: LoginContract.Presenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        LoginContract.ItemView {

        private val loginTextView = itemView.findViewById<TextView>(R.id.login_text_view)

        private val imageViewEdit = itemView.findViewById<ImageView>(R.id.image_view_edit)
        private val imageViewDelete = itemView.findViewById<ImageView>(R.id.image_view_delete)

        private lateinit var currentGitProject: GitProjects


        override fun bindItem(gitProject: GitProjects) {
            currentGitProject = gitProject
            loginTextView.text = gitProject.login
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = LayoutInflater.from(parent.context).inflate(R.layout.item_git_projects, parent, false)
        return MyViewHolder(itemBinding)
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