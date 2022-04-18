package com.geekbrains.githubclient.ui

import androidx.fragment.app.Fragment

interface NavigationActivity {
    fun navigationTo(fragment: Fragment, withTransaction: Boolean = false)
}
