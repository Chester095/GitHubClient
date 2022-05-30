package com.geekbrains.githubclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.data.appModule
import com.geekbrains.githubclient.utils.ViewModelStore
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    val viewModelStore by lazy { ViewModelStore() }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }

}


val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
