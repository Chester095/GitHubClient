package com.geekbrains.githubclient.data

import com.geekbrains.githubclient.data.db.AccountEntity
import com.geekbrains.githubclient.domain.entities.Account

fun convertAccountDtoToEntity(account: AccountEntity): Account {
    return Account(
        uid = account.uid,
        login = account.login,
        email = account.email
    )
}