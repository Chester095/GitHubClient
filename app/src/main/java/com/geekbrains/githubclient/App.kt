package com.geekbrains.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.di.AppDependenciesComponent
import com.geekbrains.githubclient.di.DaggerAppDependenciesComponent
import com.geekbrains.githubclient.utils.ViewModelStore

class App : Application() {
    val viewModelStore by lazy { ViewModelStore() }
    lateinit var appDependenciesComponent: AppDependenciesComponent

    override fun onCreate() {
        super.onCreate()
        appDependenciesComponent = DaggerAppDependenciesComponent
            .builder()
            .build()
    }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
