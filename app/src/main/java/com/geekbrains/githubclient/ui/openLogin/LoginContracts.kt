package com.geekbrains.githubclient.ui.openLogin

interface LoginContracts {

    interface ViewModelContract {
        fun getProjects(name: String)

        fun getProjectsRetrofit(name: String)
    }

}