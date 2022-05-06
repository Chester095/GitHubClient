package com.geekbrains.githubclient.ui.login

import com.geekbrains.githubclient.domain.GitProjectEntity

interface LoginContract {
    interface View {
        fun initViews(adapterLogin: LoginRecyclingAdapter)
        fun setSuccess()
        fun setError(error: String)
        fun setTextView(contactLogin: String?)
    }

    interface Presenter {
        fun onOpenLogin(gitProject: GitProjectEntity)
    }
}