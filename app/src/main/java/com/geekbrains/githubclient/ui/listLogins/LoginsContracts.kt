package com.geekbrains.githubclient.ui.listLogins

import android.content.Context

interface LoginsContracts {

    interface ViewModelContract {
        fun getLogin(context: Context)
    }
}