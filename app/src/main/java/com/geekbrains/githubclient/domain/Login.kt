package com.geekbrains.githubclient.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Login(
    val id: Int = 0,
    val login: String
) : Parcelable
