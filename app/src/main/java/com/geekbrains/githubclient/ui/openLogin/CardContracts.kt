package com.geekbrains.githubclient.ui.openLogin

interface CardContracts {

    interface ViewModelContract {
        fun getProjects(name: String)

        fun getProjectsRetrofit(name: String)
    }

}