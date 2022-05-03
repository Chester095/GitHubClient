package com.geekbrains.githubclient.ui.login

import android.content.Context
import com.geekbrains.githubclient.data.DataHandlerGitProject
import com.geekbrains.githubclient.domain.GitProjects

class LoginPresenter(_view: LoginContract.View, context: Context) : LoginContract.Presenter {

    private var view: LoginContract.View = _view
    private var dataHandler = DataHandlerGitProject(context)
    private var adapterLogin: LoginRecyclingAdapter = LoginRecyclingAdapter(this)

    init {
        view.initViews(adapterLogin)
    }

    override val itemCount: Int
        get() = dataHandler.getAllGitProjects().size

    override fun onOpenLogin(contact: GitProjects) {
        TODO("Not yet implemented")
    }

    override fun getAllGitProjects(): List<GitProjects> {
        return dataHandler.getAllGitProjects()
    }

    override fun onBindItemView(itemView: LoginRecyclingAdapter.MyViewHolder, pos: Int) {
        itemView.bindItem(getAllGitProjects()[pos])
    }

}