package com.geekbrains.githubclient.ui.login

import com.geekbrains.githubclient.domain.GitProjects

interface LoginContract {
    interface View {
        fun initViews(adapterLogin: LoginRecyclingAdapter)
        fun setSuccess()
        fun setError(error: String)
        fun setTextView(contactLogin: String?)
    }

    interface ItemView {
        fun bindItem(gitProject: GitProjects)
    }

    interface Presenter {
        val itemCount: Int
        fun onOpenLogin(gitProject: GitProjects)
        fun onBindItemView(itemView: LoginRecyclingAdapter.GitProjectsViewHolder, pos: Int)
        fun getAllGitProjects(): List<GitProjects>
    }
}