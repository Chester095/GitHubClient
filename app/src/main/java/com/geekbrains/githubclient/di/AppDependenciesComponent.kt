package com.geekbrains.githubclient.di

import com.geekbrains.githubclient.ui.listLogins.LoginsFragment
import com.geekbrains.githubclient.ui.openLogin.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppDependenciesModule::class
    ]
)
interface AppDependenciesComponent {
    fun injectLogins(loginsFragment: LoginsFragment)
    fun injectLogin(loginFragment: LoginFragment)
}