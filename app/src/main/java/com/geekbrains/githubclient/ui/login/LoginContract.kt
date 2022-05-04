package com.geekbrains.githubclient.ui.login

import com.geekbrains.githubclient.domain.GitProjectEntity

interface LoginContract {
    interface View {
        fun initViews(adapterLogin: LoginRecyclingAdapter)
        fun setSuccess()
        fun setError(error: String)
        fun setTextView(contactLogin: String?)
    }

    interface ItemView {
        fun bindItem(gitProject: GitProjectEntity)
    }

    interface Presenter {
        fun onOpenLogin(gitProject: GitProjectEntity)
        fun onBindItemView(itemView: LoginRecyclingAdapter.Git, pos: Int)
//        fun getAllGitProjects(): List<GitProjects>
    }
}