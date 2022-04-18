package com.geekbrains.githubclient.data

import com.geekbrains.githubclient.data.db.AccountEntity
import com.geekbrains.githubclient.data.db.AccountsDao
import com.geekbrains.githubclient.domain.ILoginApi
import com.geekbrains.githubclient.domain.entities.Account
import com.geekbrains.githubclient.ui.utils.*
import javax.security.auth.login.LoginException

class RoomLoginApi(private val localDataSource: AccountsDao) : ILoginApi {

    fun getAllAccounts(): List<AccountEntity> = localDataSource.getAllAccountData()

    private fun checkData(login: String?, password: String?, email: String?){
        if (login != null && login.isEmpty()){
            throw LoginException()
        }

        if (password != null && password.isEmpty()){
            throw PasswordException()
        }

        if (email != null && email.isEmpty()){
            throw EmailException()
        }
    }

    override fun login(login: String, password: String) : Account {
        checkData(login, password, null)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if (account.login == login && account.password == password) {
                return convertAccountDtoToEntity(account)
            }
        }
        throw SingInException()
    }

    override fun register(login: String, password: String, email: String) : Account {
        checkData(login, password, null)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if(account.login == login && account.email == email) {
                throw RegistrationException()
            }
        }
        val newAccount = AccountEntity(uid=null, login = login, password = password, email = email)
        localDataSource.registration(newAccount)
        return convertAccountDtoToEntity(newAccount)
    }

    override fun forgotPassword(email: String) : Account {
        checkData(null, null, email)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if (account.email == email) {
                return convertAccountDtoToEntity(account)
            }
        }
        throw ForgetPasswordException()
    }
}