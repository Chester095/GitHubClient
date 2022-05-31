package com.geekbrains.githubclient.ui.listLogins

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.domain.Login

class LoginsRecyclingAdapter(
    private val itemClickCallback: (Login) -> Unit):
    RecyclerView.Adapter<LoginsViewHolder>() {

    private var login: List<Login> = listOf()

    fun setLogin(data: List<Login>) {
        login = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LoginsViewHolder.createView(parent)

    override fun onBindViewHolder(holder: LoginsViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickCallback)
    }

    private fun getItem(pos: Int) = login[pos]

    override fun getItemCount(): Int = login.size


}