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

/*    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = LayoutInflater.from(parent.context).inflate(R.layout.item_login, parent, false)
        return LoginViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoginViewHolder) {
            mainPresenter.onBindItemView(holder, position)
        }
    }*/

}