package com.geekbrains.githubclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AccountEntity::class], version = 2)
abstract class AccountsDb : RoomDatabase() {
    abstract fun accountDao(): AccountsDao
}